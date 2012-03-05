#include "LecteurBD.h"


LecteurBD::LecteurBD( RepertoireActeurs &lesActeurs, RepertoireFilms &lesFilms ) :
  lesActeurs(&lesActeurs),
  lesFilms(&lesFilms)
{}

void LecteurBD::lireActeur( istream & is )
{
  string s;

  getline( is, s );
  size_t debut = s.find("{");
  size_t fin = s.find("}");
  string nom_acteur = s.substr( debut+1, fin-debut-1 );
  Acteur * A = (*lesActeurs)[ nom_acteur ];
  while  ( string::npos != ( debut = s.find("{",fin) ) )
    {
      fin = s.find("}", debut+1);
      string titre_film = s.substr( debut+1, fin-debut-1 );
      Film * F = (*lesFilms)[ titre_film ];
      A->ajouteFilm( F );
      F->ajouteActeur( A );
    }

}

void LecteurBD::lireFichier( const char * fichier )
{
  string s;
  ifstream lecture;
  lecture.open(fichier);
  int nb_lignes;

  // On identifie d'abord l'entête du fichier de données et on la passe
  string commande; 
  commande  = string("wc -l ");
  commande += string(fichier); 
  commande += string(" | cut -d: -f1");

  FILE * f = popen( commande.c_str() , "r" );
  fscanf( f, "%d", &nb_lignes );
  pclose( f );

  Barre_Progression bp( nb_lignes, 50 );
  cerr << "Lecture du fichier : " << fichier << endl;
  for (int i=0; i<nb_lignes; ++i)
    {
      lireActeur( lecture );
      bp.progresse();
    }
}

