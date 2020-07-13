public class Elevator {
    // tworzymy 3 wątki
    static ElevatorCar car = new ElevatorCar();
    static ExternalPanelsAgent externalPanelAgent = new ExternalPanelsAgent(car);
    static InternalPanelAgent internalPanelAgent = new InternalPanelAgent(car);

    // symulacja przywołania windy z panelu zewnętrznego
    static void makeExternalCall(int atFloor,boolean directionUp){
        try {
            externalPanelAgent.input.put(new ExternalPanelsAgent.ExternalCall(atFloor,directionUp));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // symulacja wyboru pietra w panelu wewnętrznym
    static void makeInternalCall(int toFloor){
        try {
            internalPanelAgent.input.put(new InternalPanelAgent.InternalCall(toFloor));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // uruchomienie wątków
    static void init(){
        car.start();
        externalPanelAgent.start();
        internalPanelAgent.start();
    }

    // miesjce na kod testowy
    public static void main(String[] args) throws InterruptedException {
        init();
        makeExternalCall(4,false);
        Thread.currentThread().sleep(100);
        makeInternalCall(2);

        /*
        2.
        makeExternalCall(5,false);
        makeInternalCall(4);
        makeInternalCall(6);
        Thread.currentThread().sleep(2000);
        makeInternalCall(3);
        makeExternalCall(2,false);

        3.
        makeInternalCall(4);
        makeExternalCall(1,false);
        makeExternalCall(6,true);
        makeInternalCall(4);

        4.
        makeInternalCall(6);
        Thread.currentThread().sleep(2000);
        makeInternalCall(4);
        makeExternalCall(1,false);
        makeInternalCall(3);

        5.
        makeExternalCall(3,false);
        makeInternalCall(4);
        Thread.currentThread().sleep(2000);
        makeExternalCall(1,false);
        makeInternalCall(3);
         */
    }
}
