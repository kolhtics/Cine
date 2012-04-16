package Algorithme;


public class Test {
	public static int Distance(String chaine1, String chaine2){
		// d est un tableau de longueurChaine1+1 rangées et longueurChaine2+1 colonnes
		int[][] d = new int[chaine1.length()+1][chaine2.length()+1];
		int i, j, cout;
		
		for(i=0; i<chaine1.length(); i++){
			d[i][0] = i;
		}
		
		for(j=0; j<chaine2.length(); j++){
			d[0][j] = j;
		}
		
		for(i=1; i<=chaine1.length(); i++){
			for(j=1; j<=chaine2.length(); j++){
				//Character c1 = chaine1.charAt(i-1);
				//Character c2 = chaine2.charAt(j-1);
				//if(c1.equals(c2)){
				if(((Character)chaine1.charAt(i-1)).equals((Character)chaine2.charAt(j-1))){
					cout = 0;
				}
				else{
					cout = 1;
				}
				
				d[i][j] = min(d[i-1][j]+1, min(d[i][j-1]+1, d[i-1][j]+cout));
			}
		}
		/*for(i=0; i<chaine1.length(); i++){
			for(j=0; j<chaine2.length(); j++){
				System.out.print(" "+d[i][j]);
			}
			System.out.print("\n");
			System.out.print(" "+d[i][j]);
		}*/
		return d[chaine1.length()][chaine2.length()];
	}
	
	
	public static int min(int a, int b){
		if(a>b){
			return b;
		}
		else{
			return a;
		}
	}
	
	
	public static void main( String[] args ){
		System.out.println("\n Distance : "+Distance("niche", "chiens"));
		
	}

}
