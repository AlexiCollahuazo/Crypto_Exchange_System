package com.globant.controller.MenuCrypto;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.model.cryptocurrencies.Cryptocurrencies;
import com.globant.model.user.User;
import com.globant.service.Orders.BuyOrderService;
import com.globant.service.cryptocurrencies.CryptoService;
import com.globant.service.user.UserSingleton;
import com.globant.service.user.UserWalletService;
import com.globant.view.MenuCryptoView;

import java.math.BigDecimal;

public class BuyOrderController implements ControllerExecuteInterface {

    private final MenuCryptoView view;
    private final CryptoService cryptoService;
    private final UserWalletService wallet;
    private final BuyOrderService buyOrder;
    private final UserSingleton user;

    public BuyOrderController( MenuCryptoView view) {
        this.view = view;
        this.cryptoService = CryptoService.getInstance();
        this.buyOrder = new BuyOrderService();
        this.user = UserSingleton.getInstance();
        this.wallet = new UserWalletService(user);

    }

    public void execute() {
        try
        {
            int id = user.getCurrentUser().getID();

            //de aqui Ver como mejorar esto(Crear clase autenticadora)
            String Type = view.CryptoTypeView("Type of cryptocurrency from the following list: ");
            String typeCrypto = Type.toUpperCase();
            BigDecimal amount = view.getdataBigdecimal(" amount ");
            BigDecimal maxPrice = view.getdataBigdecimal("maximum price ");
            Cryptocurrencies crypto = cryptoService.getCryptocurrencies(typeCrypto);

            if(amount.compareTo(BigDecimal.ZERO) <= 0)
            {
                view.showError("Cannot be 0 or less than 0");
                return;
            }
            if (crypto == null) {
                view.showError("The cryptocurrency you are looking for is not listed");
                return;
            }
            BigDecimal totalAmount = crypto.getPrice().multiply(amount);
            if (!wallet.checkbalance(wallet,totalAmount))
            {
                view.showError("You don't have enough money to place the buy order.");
                return;
            }
            buyOrder.placeBuyOrder(id,typeCrypto, amount,maxPrice,wallet);
            view.showSuccessMessage("Sell Order created and placed");
            // hasta aqui



        } catch (Exception e)
        {
            view.showError("ERROR EN EL BUY");

        }
    }
}
