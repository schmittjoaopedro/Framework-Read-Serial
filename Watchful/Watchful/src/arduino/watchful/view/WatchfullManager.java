package arduino.watchful.view;

import arduino.watchful.communicate.SerialFactoryUtil;
import arduino.watchful.communicate.SerialPortException;
import arduino.watchful.core.DefinitionException;
import arduino.watchful.service.ComponentPanel;
import arduino.watchful.service.WireComponents;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WatchfullManager {

    private WatchfulView view;
    private WireComponents controller;
    private ArrayList<ComponentPanel> components;

    public WatchfullManager(WatchfulView instance) {
        this.view = instance;


        try {
            this.init();
            this.defineButtonListeners();
        } catch (Exception er) {
            System.out.println(er.getMessage());
        }
    }

    protected final void init() throws SerialPortException {
        this.getView().getComboPorts().removeAllItems();
        for(String port : SerialFactoryUtil.listAvailablesCOM()){
            this.getView().getComboPorts().addItem(port);
        }
        this.components = this.getView().getPanels();
    }

    protected WatchfulView getView() {
        return this.view;
    }

    protected WireComponents getController() {
        return this.controller;
    }

    protected void setController(WireComponents wired) {
        this.controller = wired;
    }

    protected final void defineButtonListeners() {

        //Methods...
        this.getView().getBtOpen().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    togglePositions();
                    if (getView().getBtOpen().getText().equals("OPEN")) {
                        setController(new WireComponents(getView().getComboPorts().getSelectedItem().toString()));
                        getView().getBtOpen().setBackground(Color.GREEN);
                        getView().getBtOpen().setText("CLOSE");
                        togglePositions("refresh");
                        getView().getBtDefine().setEnabled(true);
                    } else {
                        getView().getBtOpen().setText("CLOSE");
                        getController().getSession().close();
                        System.exit(0);
                    }
                } catch (Exception er) {
                    getView().getBtOpen().setBackground(Color.RED);
                }

            }
        });


        this.getView().getBtDefine().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 
                for(ComponentPanel d : components){
                    if(d.isDefined() == true) {
                        getController().addControlled(d.getEletronic());
                    }
                }
                try {
                    getController().define();
                    togglePositions("executeGroup","analyse","define");
                }catch(DefinitionException | SerialPortException er){
                    System.out.println(er.getMessage());
                }
            }
        });

        this.getView().getBtExecuteGroup().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getController().execute();
                }catch(DefinitionException | SerialPortException er){
                    System.out.println(er.getMessage());
                }
            }
        });
        
        this.getView().getBtAnalyse().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Thread analiser = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            while(true) {
                                getController().analiser();
                                for(ComponentPanel d : components){
                                    if(d.isDefined() == true) {
                                        d.refresh();
                                    }
                                }
                                Thread.sleep(500);
                            }
                        }catch(Exception er){
                            System.out.println(er.getMessage());
                        }
                    }
                });
                if(getView().getBtAnalyse().getText().equals("ANALYSE")) {
                    analiser.start();
                    getView().getBtAnalyse().setBackground(Color.ORANGE);
                    getView().getBtAnalyse().setText("ANALYSING...");
                }else{
                    analiser.interrupt();
                    getView().getBtAnalyse().setBackground(Color.GRAY);
                    getView().getBtAnalyse().setText("ANALYSE");
                }
                
            }
        });
        
        this.getView().getBtReset().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getController().getSession().write("R");
                    getView().getBtDefine().setEnabled(true);
                    getView().getBtExecuteGroup().setEnabled(false);
                    getView().getBtAnalyse().setEnabled(false);
                } catch (SerialPortException ex) {
                    Logger.getLogger(WatchfullManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        this.getView().getBtRefresh().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getView().getComboPorts().removeAllItems();
                try {
                    for(String port : SerialFactoryUtil.listAvailablesCOM()){
                        getView().getComboPorts().addItem(port);
                    }
                } catch (SerialPortException ex) {
                    Logger.getLogger(WatchfullManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    
    
    private void togglePositions(String... buttons){
        for(String btn : buttons) {
            switch(btn) {
                case "open":
                    if(this.getView().getBtOpen().isEnabled())
                        this.getView().getBtOpen().setEnabled(false);
                    else
                        this.getView().getBtOpen().setEnabled(true);
                    break;
                case "refresh":
                    if(this.getView().getBtRefresh().isEnabled())
                        this.getView().getBtRefresh().setEnabled(false);
                    else
                        this.getView().getBtRefresh().setEnabled(true);
                    break;
                case "define":
                    if(this.getView().getBtDefine().isEnabled())
                        this.getView().getBtDefine().setEnabled(false);
                    else
                        this.getView().getBtDefine().setEnabled(true);
                    break;
                case "executeGroup":
                    if(this.getView().getBtExecuteGroup().isEnabled())
                        this.getView().getBtExecuteGroup().setEnabled(false);
                    else
                        this.getView().getBtExecuteGroup().setEnabled(true);
                    break;
                case "reset":
                    if(this.getView().getBtReset().isEnabled())
                        this.getView().getBtReset().setEnabled(false);
                    else
                        this.getView().getBtReset().setEnabled(true);
                    break;
                case "analyse":
                    if(this.getView().getBtAnalyse().isEnabled())
                        this.getView().getBtAnalyse().setEnabled(false);
                    else
                        this.getView().getBtAnalyse().setEnabled(true);
                    break;
            }
        }
    }
}
