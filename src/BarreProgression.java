import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.lang.Thread;
import java.io.File;

/**
 * Classe servant à visualiser une progression.
 *
 * @author Xavier Provençal
 *
 * Cette classe ne fait que lancer le script bash "BarreProgression.sh" puis
 * lui signale des incréments de progressions.
 */

public class BarreProgression extends Thread
{

	// Chemin vers le script "BarreProgression.sh" 
	private final String fichier = "./BarreProgression.sh";

	// Constructeur à partir d'un objectif ( nombre d'incréments à effectuer )
	// et le nombre de colonnes affichées.
	public BarreProgression( int objectif, int nb_col )
	{
		max_value = objectif;
		nb_col_total = nb_col;

		// Teste si le fichier est accessible pour exécution.
		// S'il ne l'est pas, 
		File test = new File( fichier );
		fichierOk = test.canExecute();

		if ( fichierOk )
		{
			String commande = fichier + " " + objectif + " " + nb_col_total;
			try 
			{
				p = Runtime.getRuntime().exec( commande );
				osw = new OutputStreamWriter ( p.getOutputStream() );
			}
			catch ( IOException e ) { }
		}
	}

	// Lance le script
	public void run()
	{
		int size = 0;
		if ( fichierOk ) 
		{
			try 
			{
				InputStream is = p.getInputStream();
				byte[] buffer = new byte[1024];
				while ( (size = is.read(buffer)) != -1) 
				{
					System.out.write(buffer, 0, size);
				}
				p.waitFor();
				p.destroy();
			}
			catch ( IOException e ) { }
			catch ( InterruptedException e ) {}
		}
	}

	// Signale un incrément au script
	public void progresse ( )
	{
		if ( fichierOk ) 
		{			
			try 
			{
				osw.write( "1\n" );
				osw.flush();
			}
			catch (IOException e)
			{}
		}
	}


	private int max_value;
	private int nb_col_total;
	private OutputStreamWriter osw;
	private Process p;
	private boolean fichierOk;

}



