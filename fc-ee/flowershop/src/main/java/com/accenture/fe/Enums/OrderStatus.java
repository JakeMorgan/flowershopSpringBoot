package com.accenture.fe.Enums;
    public enum OrderStatus {
        CREATED,
        SENT,
        COMPLITED;

        public OrderStatus next() {
            switch (this) {
                case CREATED:
                    return SENT;
                case SENT:
                    return COMPLITED;
            }
            return this;
        }
    }
