/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

/**
 *
 * @author Mauro
 */
public class cronometer {
    private long delta , lastime;
    private long time;
    private boolean runing;
    
    
    
    public cronometer(){
     delta=0;
     lastime=0;
     runing=false;
  
    }
    
    public void run(long time){
    runing = true;
    this.time = time; 
    }
    public void update(){
    if(runing)
       delta +=System.currentTimeMillis() - lastime;
        if (delta>=time) {
            runing=false;   
           delta=0;
        }
    lastime = System.currentTimeMillis();
    
    }
    public boolean isRunning(){
        return runing;
    
    
    }
    
}
