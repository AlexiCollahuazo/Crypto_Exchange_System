package com.globant.controller.MenuCrypto;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.model.cryptocurrencies.Cryptocurrencies;
import com.globant.service.Orders.BuyOrderService;
import com.globant.service.Orders.SellOrderService;
import com.globant.service.cryptocurrencies.CryptoService;
import com.globant.service.user.UserSingleton;
import com.globant.service.user.UserWalletService;
import com.globant.view.MenuCryptoView;

import java.math.BigDecimal;

public class SellOrderController implements ControllerExecuteInterface {

    private final MenuCryptoView view;
    private final CryptoService cryptoService;
    private final UserWalletService wallet;
    private SellOrderService sellOrder;
    private UserSingleton user;

    public SellOrderController( MenuCryptoView view) {
        this.view = view;
        this.cryptoService = CryptoService.getInstance();
        this.sellOrder = new SellOrderService();
        this.user = UserSingleton.getInstance();
        this.wallet = new UserWalletService(user);

    }


    @Override
    public void execute() {
        try
        {

        int id = user.getCurrentUser().getID();
        //de aqui Ver como mejorar esto(Crear clase autenticadora)
        String Type = view.CryptoTypeView("crypto type:");
        String typeCrypto = Type.toUpperCase();
        BigDecimal amount = view.getdataBigdecimal(" amount to sell: ");
        BigDecimal maxPrice = view.getdataBigdecimal("minimum price ");
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

        if (!wallet.Checkcryptocurrencies(wallet,typeCrypto,amount))
        {
            view.showError("You don't have enough cryptocurrencies to sell");
            return;
        }
        sellOrder.placeSellOrder(id,typeCrypto, amount,maxPrice,wallet);
        view.showSuccessMessage("Sell Order created and placed");
        // hasta aqui


    } catch (Exception e){
        view.showError("ERROR EN EL SELL");

    }

    }
}
