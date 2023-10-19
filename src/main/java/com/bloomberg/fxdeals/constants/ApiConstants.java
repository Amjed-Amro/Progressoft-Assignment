package com.bloomberg.fxdeals.constants;

public class ApiConstants {
    public final static class RESPONSE {
        public final static class CODE {
            public static final String SUCCESS = "000";
            public static final String FAILED = "009";
            public static final String VALIDATION = "001";
            public static final String TECHNICAL = "999";
        }

        public final static class MESSAGE {
            public static final String TECHNICAL_SERVICE_ERROR = "Sorry! Service Technical Error";
            public static final String REQUEST_SUCCESS = "Request Processed Successfully";
            public static final String INVALID_REQUEST = "Invalid Request";
            public static final String MISSING_HEADER = "Invalid Request, Missing Http Headers";
            public static final String ALREADY_CONFIGURED = "Already Configured";
            public static final String FX_DEAL_NOT_FOUND = "fx deal not found";


        }

        public final static class STATUS {
            public static final String FAILED = "FAILED";
            public static final String SUCCESS = "SUCCESS";
            public static final String REJECTED = "REJECTED";
        }
    }
}
