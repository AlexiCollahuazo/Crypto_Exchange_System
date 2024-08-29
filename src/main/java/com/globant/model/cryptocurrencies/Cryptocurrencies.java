package com.globant.model.cryptocurrencies;

import java.math.BigDecimal;

public class Cryptocurrencies {
    private String type;
    private BigDecimal price;
    private BigDecimal Amount;

    public Cryptocurrencies(Builder builder) {
        this.type = builder.type;
        this.price = builder.price;
        this.Amount = builder.Amount;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }



    public static class Builder {
        private String type;
        private BigDecimal price;
        private BigDecimal Amount;

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {
            Amount = amount;
            return this;
        }

        public Cryptocurrencies build() {
            return new Cryptocurrencies(this);
        }
    }
}
