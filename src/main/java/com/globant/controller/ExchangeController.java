package com.globant.controller;

import com.globant.model.user.Cryptocurrencies;
import com.globant.service.*;
import com.globant.view.ConsoleView;

import java.math.BigDecimal;

public class ExchangeController implements ControllerExecuteInterface {
    private ConsoleView view;
    private final CryptoService cryptoService;
    private  UserWalletService wallet;


    public ExchangeController(ConsoleView view) {
        this.view = view;
        this.cryptoService = CryptoService.getInstance();
        this.wallet = new UserWalletService(UserSingleton.getInstance());
    }


    public void execute() {
        try
        {
            String Type = view.ExchangeTypeView();
            String TypeExchange = Type.toUpperCase();
            BigDecimal ExchangeAmount = view.ExchangeAmountView();
            if(ExchangeAmount.compareTo(BigDecimal.ZERO) <= 0)
            {
                view.showError("Cannot be 0 or less than 0");
                return;
            }

            Cryptocurrencies crypto = cryptoService.getCryptocurrencies(TypeExchange);
            if (crypto == null) {
                view.showError("The cryptocurrency you are looking for is not listed");
                return;
            }

            BigDecimal totalamount = crypto.getPrice().multiply(ExchangeAmount);

            if (!wallet.checkbalance(wallet,totalamount))
            {
                view.showError("You don't have enough money to make the purchase");
                return;
            }

            if (!cryptoService.withdrawCryptos(TypeExchange, ExchangeAmount))
            {
                view.showError("There are not enough cryptocurrencies for sale, " + crypto.getAmount()+ " Left ");
                return;
            };

            wallet.withdraw(totalamount);
            wallet.addCrypto(TypeExchange,ExchangeAmount);
            view.showSuccessMessage("Exchange registered of: " + ExchangeAmount +" "+ TypeExchange);

        } catch (Exception e)
        {
            view.showError("Incorrect specified amount");
        }
    }
}
