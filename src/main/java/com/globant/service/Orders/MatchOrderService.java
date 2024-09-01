package com.globant.service.Orders;

import com.globant.model.orders.Order;
import com.globant.model.orders.OrdersBook;
import com.globant.model.user.User;
import com.globant.model.user.UserSingleton;
import com.globant.view.MenuCryptoView;

import java.math.BigDecimal;
import java.util.Map;

public class MatchOrderService {

    private final MenuCryptoView view;

    private final BuyOrderService buyOrderService;
    private final SellOrderService sellOrderService;
    private final OrdersBook ordersBook;
    private final UserSingleton user;


    public MatchOrderService(MenuCryptoView view) {
        this.view = view;
        this.buyOrderService = new BuyOrderService();
        this.sellOrderService = new SellOrderService();
        this.user = UserSingleton.getInstance();
        this.ordersBook = OrdersBook.getInstance();
    }
    public void MatchOrders() {
        try
        {
            Map< Integer, Order> buyOrders = buyOrderService.getBuyOrders();
            Map< Integer, Order> sellOrders = sellOrderService.getSellOrders();

            for (Map.Entry<Integer, Order> buyEntry : buyOrders.entrySet()) {
                Order buyOrder = buyEntry.getValue();
                for (Map.Entry<Integer, Order> sellEntry : sellOrders.entrySet()) {
                    Order sellOrder = sellEntry.getValue();


                    User buyer = user.getUserById(buyOrder.getUserId());
                    User seller =user.getUserById(sellOrder.getUserId());
                    BigDecimal Total = buyOrder.getMaxOrMinprice().min(sellOrder.getMaxOrMinprice());
                    BigDecimal amountBuyOrder = buyOrder.getAmount();
                    String typeBuyOrder = buyOrder.getCryptotype();
                    BigDecimal amountSellOrder = sellOrder.getAmount();
                    String typeSellOrder = sellOrder.getCryptotype();
                    boolean check = CheckingOrders(buyOrder, sellOrder);
                    if (check) {
                        seller.getWallet().substractCrypto(typeSellOrder, amountSellOrder);
                        buyer.getWallet().addCrypto(typeBuyOrder, amountBuyOrder);

                        view.showSuccessMessage("Matching orders of: " + amountBuyOrder + " " + typeBuyOrder + " per " + Total);
                        buyer.getWallet().withdraw(Total);
                        seller.getWallet().deposit(Total);
                        ordersBook.removeBuyOrder(buyEntry.getKey());
                        ordersBook.removeSellOrder(sellEntry.getKey());
                        return;
                    }
                }
            }

        } catch (Exception e)
        {
            view.showError("Error in the match ");

        }
    }


// Aqui van las condiciones que vamos a utilizar
    private boolean CheckingOrders(Order buyOrder, Order sellOrder) {
        return buyOrder.getCryptotype().equals(sellOrder.getCryptotype())
                && buyOrder.getAmount().compareTo(sellOrder.getAmount()) == 0
                && buyOrder.getMaxOrMinprice().compareTo(sellOrder.getMaxOrMinprice()) >= 0;
    }






}
