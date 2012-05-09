package algorithme;

import interfaceGraphique.PanelBot;

public class Point extends Thread {
	private PanelBot panelBot;
	private Boolean b = false;
	private String animation[] = {"recherche", "recherche.", "recherche..", "recherche..."};

	
	public Point(PanelBot panelBot){
		this.panelBot = panelBot;
		this.start();
	}
	
	public void run() {		
			try{
				methode();
			}catch(InterruptedException e){}
	}
	
	public void methode() throws InterruptedException{
		while(!b){
			isTrouve();
			
			for(int i = 0; i<animation.length; i++){
				
				panelBot.setLabelRecherche(animation[i]);
				System.out.println(animation[i]);
				Thread.sleep(250);
				
				if(isTrouve()){
					break;
				}
			}
			panelBot.setLabelRecherche("Recherche terminée");
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
