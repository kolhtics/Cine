package cinemaPackage;
import interfaceGraphique.BoiteDialog;

import java.io.*;


public class LecteurLigneParLigne {

	private BufferedReader b;
	private boolean termine;

	public LecteurLigneParLigne(String fichier) 
	{
		termine = false;
		try {
			InputStream ips = new FileInputStream(fichier); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			b = new BufferedReader(ipsr);
		}		
		catch (Exception e){
			//System.out.println(e.toString());
			new BoiteDialog(null, "Les fichers de bases de donn�e n'ont pas �t�s trouv�s !!");
			System.exit(0);
		}
	}

	public boolean lectureTermine()
	{
		return termine;
	}

	public String ligneSuivante()
	{
		String ligne;
		try {
			if ((ligne = b.readLine()) != null ){
				return ligne;
			}
			termine = true;
			b.close();
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
		return "";
	}
}

