/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
 *
 * @author SLunsford
 */
public class Job extends Thing implements Runnable {
    double duration;
    ArrayList<String> requirements;
    boolean goFlag = true;
    boolean noKillFlag = true;
    JProgressBar jobProgress;
    JButton jbGo = new JButton("Stop");
    JButton jbKill = new JButton("Cancel");
    Status status = Status.SUSPENDED;
    
    enum Status {RUNNING, SUSPENDED, WAITING, DONE};

   
    public Job(JPanel jobsPanel, Scanner sc){
        super(sc);
        duration = sc.nextFloat();
        requirements = new ArrayList<>();
        while (sc.hasNext()){
            requirements.add(sc.next());
        }
        jobProgress  = new JProgressBar();
        jobProgress.setStringPainted(true);
        jobsPanel.add(jobProgress);
        jobsPanel.add(new JLabel (name, SwingConstants.CENTER));
        jobsPanel.add(jbGo);
        jobsPanel.add(jbKill);
        
        jbGo.addActionListener((ActionEvent e) -> {
            if (e.getSource() == jbGo) {
                toggleGoFlag();
            }
        });
        
        jbKill.addActionListener((ActionEvent e) -> {
            if (e.getSource() == jbKill) {
                setKillFlag();
            }
        });
        
        new Thread (this).start();
    }
    
    public void toggleGoFlag(){
        goFlag = !goFlag;
    }
    
    public void setKillFlag(){
        noKillFlag = false;
    }
    
    void showStatus(Status st) {
        
    }
    
    public void run () {
        long time = System.currentTimeMillis();
        long startTime = time;
        long stopTime = (long) (time + 1000 * duration);
        long jobTime = stopTime - time;
       
        /*synchronized(worker) {
            while (worker.busyFlag) {
                showStatus (Status.WAITING);
                try {
                    worker.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Job.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                }
                worker.busyFlag = true;
            }*/
        
            while (time < stopTime && noKillFlag) {
                try {
                    Thread.sleep (100);
                } catch (InterruptedException ex) {
                Logger.getLogger(Job.class.getName()).log(Level.SEVERE, null, ex);
            }
                if (goFlag) {
                    showStatus (Status.RUNNING);
                    time += 100;
                } else {
                    showStatus (Status.SUSPENDED);
                } 
            }
            showStatus (Status.DONE);
            /*synchronized (worker) {
                worker.busyFlag = false;
                worker.notifyAll();
            }*/
        }
            
    
    
    public String toString(){
        String st = super.toString() + " Duration: " + duration;
        return st;
    }
}
