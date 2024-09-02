package com.globant.controller.MenuCrypto;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.model.cryptocurrencies.Cryptocurrencies;
import com.globant.service.Orders.MatchOrderService;
import com.globant.service.Orders.SellOrderService;
import com.globant.service.cryptocurrencies.CryptoService;
import com.globant.model.user.UserSingleton;
import com.globant.service.user.UserWalletService;
import com.globant.view.MenuCryptoView;

import java.math.BigDecimal;

public class SellOrderController implements ControllerExecuteInterface {

    private final MenuCryptoView view;
    private final CryptoService cryptoService;
    private final UserWalletService wallet;
    private final SellOrderService sellOrder;
    private final UserSingleton user;
    private final MatchOrderService match;

    public SellOrderController( MenuCryptoView view) {
        this.view = view;
        this.cryptoService = CryptoService.getInstance();
        this.sellOrder = new SellOrderService();
        this.user = UserSingleton.getInstance();
        this.wallet = new UserWalletService(user);
        this.match = new MatchOrderService(view);

    }

    @Override
    public void execute() {
        try
        {
         // Call the views to get the data
        int id = user.getCurrentUser().getID();
        String Type = view.CryptoTypeView("Type of cryptocurrency from the following list:");
        String typeCrypto = Type.toUpperCase();
        BigDecimal amount = view.getdataBigdecimal("Amount of cryptocurrencies to be sold: ");
        BigDecimal maxPrice = view.getdataBigdecimal("Minimum price to sell: ");
        Cryptocurrencies crypto = cryptoService.getCryptocurrencies(typeCrypto);

        // Customized messages for each error
        if(amount.compareTo(BigDecimal.ZERO) <= 0)
        {
            view.showError("Cannot be 0 or less than 0");
            return;
        }
        if (crypto == null) {
            view.showError("The cryptocurrency you are looking for is not listed");
            return;
        }
        if (!wallet.Checkcryptocurrencies(wallet,typeCrypto,amount))
        {
            view.showError("You don't have enough cryptocurrencies to sell");
            return;
        }
        //If everything is correct, create the sell order
        sellOrder.placeSellOrder(id,typeCrypto, amount,maxPrice,wallet);
        view.showSuccessMessage("Sell Order created and placed");
        // Once created, check if they match.
        match.MatchOrders();
    } catch (Exception e){
        view.showError("Cryptocurrencies are insufficient");

    }

    }
}
