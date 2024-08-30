package com.globant.controller.MenuCrypto;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.model.cryptocurrencies.Cryptocurrencies;
import com.globant.service.cryptocurrencies.CryptoService;
import com.globant.service.user.UserSingleton;
import com.globant.service.user.UserWalletService;
import com.globant.view.MenuCryptoView;

import java.math.BigDecimal;

public class ExchangeController implements ControllerExecuteInterface {
    private final CryptoService cryptoService;
    private final UserWalletService wallet;
    private final MenuCryptoView view;

    public ExchangeController( MenuCryptoView view) {
        this.view = view;
        this.cryptoService = CryptoService.getInstance();
        this.wallet = new UserWalletService(UserSingleton.getInstance());
    }


    public void execute() {
        try
        {

            String Type = view.CryptoTypeView("Please enter a cryptocurrency from the following list:");
            String TypeExchange = Type.toUpperCase();
            BigDecimal ExchangeAmount = view.ExchangeAmountView("Enter the amount of cryptocurrencies you want");
            Cryptocurrencies crypto = cryptoService.getCryptocurrencies(TypeExchange);

            // De aqui. Ver como mejorar esto
            if(ExchangeAmount.compareTo(BigDecimal.ZERO) <= 0)
            {
                view.showError("Cannot be 0 or less than 0");
                return;
            }


            if (crypto == null) {
                view.showError("The cryptocurrency you are looking for is not listed");
                return;
            }

            BigDecimal totalAmount = crypto.getPrice().multiply(ExchangeAmount);

            if (!wallet.checkbalance(wallet,totalAmount))
            {
                view.showError("You don't have enough money to make the purchase");
                return;
            }

            if (!cryptoService.withdrawCryptos(TypeExchange, ExchangeAmount))
            {
                view.showError("There are not enough cryptocurrencies for sale, " + crypto.getAmount()+ " Left ");
                return;
            };

            // hasta aqui

            wallet.withdraw(totalAmount);
            wallet.addCrypto(TypeExchange,ExchangeAmount);
            view.showSuccessMessage("Exchange registered of: " + ExchangeAmount +" "+ TypeExchange);

        } catch (Exception e)
        {
            view.showError("Incorrect specified amount");
        }
    }
}
