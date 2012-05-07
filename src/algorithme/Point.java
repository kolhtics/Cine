package algorithme;

import interfaceGraphique.PanelBot;

import java.awt.Frame;

public class Point extends Thread {
	private PanelBot panelBot;
	private Boolean b = false;
	
	public Point(PanelBot panelBot){
		this.panelBot = panelBot;
		this.start();
	}
	
	public void run() {		
			try{
				methode();
			}
			catch(InterruptedException e){
				System.err.println("je bug!!");
			}
	}
	
	public void methode() throws InterruptedException{
		while(!b){
			System.out.println("============================== Debut thread =====================================");
			isTrouve();
			
			String s = "recherche";
			System.out.println(s);
			if(isTrouve()){
				break;
			}
			
			panelBot.setTexte(s);
			s+=".";
			System.out.println(s);
			Thread.sleep(250);
			if(isTrouve()){
				break;
			}
			
			panelBot.setTexte(s);
			s+=".";
			System.out.println(s);
			Thread.sleep(250);
			if(isTrouve()){
				break;
			}
			
			panelBot.setTexte(s);
			s+=".";
			System.out.println(s);
			Thread.sleep(250);
			if(isTrouve()){
				break;
			}
			
			panelBot.setTexte(s);
			s+=".";
			Thread.sleep(250);
			if(isTrouve()){
				break;
			}
			
			System.out.println("============================== Fin de thread =====================================");
		}
		
	}
	
	public Boolean isTrouve(){
		if(AlgoBot.getTrouve()){
			this.b = true;
			AlgoBot.setTrouve(false);
		}
		return b;
	}
}
