package com.globant.controller.MenuCrypto;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.model.cryptocurrencies.Cryptocurrencies;
import com.globant.service.cryptocurrencies.CryptoService;
import com.globant.service.user.UserSingleton;
import com.globant.service.user.UserWalletService;
import com.globant.view.MenuCryptoView;

import java.math.BigDecimal;

public class BuyOrderController implements ControllerExecuteInterface {

    private final MenuCryptoView view;
    private final CryptoService cryptoService;
    private final UserWalletService wallet;

    public BuyOrderController( MenuCryptoView view) {
        this.view = view;
        this.cryptoService = CryptoService.getInstance();
        this.wallet = new UserWalletService(UserSingleton.getInstance());
    }

    public void execute() {

            String Type = view.CryptoTypeView("cryptoc type:");
            BigDecimal amount = view.getdataBigdecimal(" amount ");
            BigDecimal maxPrice = view.getdataBigdecimal("maximum price ");

        try
        {
            Cryptocurrencies crypto = cryptoService.getCryptocurrencies(Type);
            if (crypto == null) {
                view.showError("The cryptocurrency you are looking for is not listed");
                return;
            }




        } catch (Exception e)
        {

        }
    }
}
