package com.accenture.fe.Enums;
    public enum OrderStatus {
        CREATED,
        SENT,
        COMPLETED;

        public OrderStatus next() {
            switch (this) {
                case CREATED:
                    return SENT;
                case SENT:
                    return COMPLETED;
            }
            return this;
        }
    }
