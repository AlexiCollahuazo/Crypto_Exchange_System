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
    private BuyOrderService buyOrder;
    private User user;

    public BuyOrderController( MenuCryptoView view) {
        this.view = view;
        this.cryptoService = CryptoService.getInstance();
        this.buyOrder = new BuyOrderService();
        this.user = UserSingleton.getInstance().getCurrentUser();
        this.wallet = new UserWalletService(UserSingleton.getInstance());
    }

    public void execute() {
        try
        {
            //de aqui Ver como mejorar esto(Crear clase autenticadora)

            String Type = view.CryptoTypeView("crypto type:");
            String typecrypto = Type.toUpperCase();
            BigDecimal amount = view.getdataBigdecimal(" amount ");
            BigDecimal maxPrice = view.getdataBigdecimal("maximum price ");
            BigDecimal  cryptosavailable = wallet.getMycryptocurrencies(typecrypto);
            Cryptocurrencies crypto = cryptoService.getCryptocurrencies(typecrypto);
            BigDecimal totalamount = crypto.getPrice().multiply(amount);


            if(amount.compareTo(BigDecimal.ZERO) <= 0)
            {
                view.showError("Cannot be 0 or less than 0");
                return;
            }


            if (crypto == null) {
                view.showError("The cryptocurrency you are looking for is not listed");
                return;
            }


            if (!wallet.checkbalance(wallet,totalamount))
            {
                view.showError("You don't have enough money to make the purchase");
                return;
            }


          /*  if (!cryptoService.withdrawCryptos(typecrypto, amount))
            {
                view.showError("There are not enough cryptocurrencies for sale, " + crypto.getAmount()+ " Left ");
                return;
            };
*/

            buyOrder.placeBuyOrder(typecrypto, amount,maxPrice,wallet);
            // hasta aqui



        } catch (Exception e)
        {
            view.showError("ERROR EN EL BUY");

        }
    }
}
