class SharedResources {
    public synchronized void doWait(){
        try {
            System.out.println("Thread is Waiting");
            wait();
            System.out.println("Thread Is Resuming");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void doNotify(){
        System.out.println("Thread Is Notifying...");
        notify();
    }
    
}
public class WaitNotifyExample {
    public static void main(String[] args) {
        SharedResources sharedResources =  new SharedResources();
        Thread waitingThread  = new Thread(()->{
            sharedResources.doWait();
        });
    }
    
}
