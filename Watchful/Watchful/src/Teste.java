
import arduino.watchful.core.Digital;
import arduino.watchful.core.EletricalComponent;
import arduino.watchful.core.Servo;
import arduino.watchful.service.WireComponents;
import java.util.Scanner;

/**
 *
 * @author joao.schmitt
 */
public class Teste {
    
    public void main(String... args){
        
        WireComponents wiredComponents = null;
        
        try{
            wiredComponents = new WireComponents("COM17");
        }catch(Exception er){
            System.out.println(er.getMessage());
        }
        
        
        
        
        EletricalComponent servo1 = new Servo();
        servo1.setPin(42);
        servo1.setCommand("90");
        
        EletricalComponent digital1 = new Digital();
        digital1.setPin(13);
        digital1.setCommand("OUTPUT");
        

        
        wiredComponents.addControlled(servo1);
        wiredComponents.addControlled(digital1);
        
        
        
        try {
            Thread.sleep(1000);
            wiredComponents.define();
        }catch(Exception er){
            er.getMessage();
        }
        
        
        int flag = 0;
        do {
          
            System.out.println("Command:\n\n");
            flag = Integer.parseInt(new Scanner(System.in).nextLine());
            
            if(flag != -1) {
                servo1.setValue(String.valueOf(flag));
                digital1.setValue("HIGH");
                try {
                    wiredComponents.execute();
                }catch (Exception er) {
                    System.out.println(er.getMessage());
                }
            }
            
        } while(flag != -1);
        
    }
    
}
