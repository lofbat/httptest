package pers.httptest.core.threads;

public class ContextService {
    private static final ThreadLocal<Context> threadContext=new ThreadLocal<Context>(){
        @Override
        public Context initialValue(){
            return new Context();
        }
    };

    private static int totalThreads;

    public static Context getContext() {
        return threadContext.get();
    }

    public static void removeContext(){ // Currently only used by JMeterThread
        threadContext.remove();
    }

    public static void replaceContext(Context context) {
        threadContext.remove();
        threadContext.set(context);
    }
}
