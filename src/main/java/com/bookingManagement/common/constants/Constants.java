package com.bookingManagement.common.constants;

public class Constants {

    public static class Controller {
        public static final String BASE_URL = "/api/booking-system";
        public static final String GET_THEATRE_VIEW = "/theatre/{theatreId}";
        public static final String GET_LIST_VIEW = "/theatres/{theatreId}";
        public static final String BOOK_TICKET = "/book";
        public static final String IN_PROGRESS = "/inprogress";
    }

    public static class Status {
        public static final String AVAILABLE = "AVAILABLE";
        public static final String BOOKED = "BOOKED";
        public static final String IN_PROGRESS = "IN_PROGRESS";
    }

    public static class Logging {
        public static final String KEY_CLASS_NAME = "className";
        public static final String KEY_METHOD_NAME = "methodName";
        public static final String KEY_METHOD_ARGUMENTS = "methodArguments";
        public static final String KEY_METHOD_PHASE = "methodPhase";
        public static final String KEY_RETURN_VALUE = "returnValue";
        public static final String KEY_TIME_ELAPSED_IN_MILLIS = "timeElapsedInMillis";
        public static final String KEY_CAUSE = "cause";
        public static final String KEY_STACK_TRACE = "stackTrace";
        public static final String KEY_LOCALIZED_MESSAGE = "localizedMessage";
        public static final String KEY_SUPPRESSED = "suppressed";
        public static final String KEY_MESSAGE = "message";
        public static final String KEY_TIME = "time";
        public static final String KEY_EPOCH_TIME = "epochTime";
        public static final String METHOD_PHASE_STARTED = "started";
        public static final String METHOD_PHASE_COMPLETED = "completed";
        public static final String METHOD_PHASE_EXCEPTION_THROWN = "exceptionThrown";
    }

    public static class Header {
        public static final String ACCESS_TOKEN = "access-token";
        public static final String USER_EMAIL = "user-email";
        public static final String USER_PASSWORD = "user-password";
    }

    public static class Fields {
        public static final String USER_ACCESS_DATA_KEY = "user_access_data";
    }
}
