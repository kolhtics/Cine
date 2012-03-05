#include "BD_Cinema.h"


const Film * Acteur::filmEnCommun(const Acteur & other) const
{
  for (EnsFilms::const_iterator i = begin(); i != end(); i++) {
      if (other.find(*i) != other.end()) {
          return *i;
      }
  }
  return NULL;
}



ostream & operator<< (ostream & os, const Acteur & a)
{
  os << a.nom << "\n";
  set<const Film *>::const_iterator i = a.begin(); 
  while (i != a.end()) 
    {
      os << " |--> " << (*i)->getTitre() << "\n";
      ++i;
    }
  return os;
}




const Acteur * Film::acteurEnCommun(const Film & other) const
{
  for (EnsActeurs::const_iterator i = begin(); i != end(); i++) {
      if (other.find(*i) != other.end()) {
          return *i;
      }
  }
  return NULL;
}


ostream & operator<< (ostream & os, const Film & m)
{
  os << m.titre << " : [";
  set<const Acteur *>::const_iterator i = m.begin(); 
  os << *i;
  while (++i != m.end()) 
    os << ", " << (*i)->getNom();
  os << "]";
  return os;
}




inline
string nettoyer_nom_acteur( const string &s)
{
  string nom = s;
  if ( nom[ nom.size() - 1 ] == ',' )
    return nom.substr( 0, nom.size() - 1 );
  else
    return nom;
}

inline
string nettoyer_nom_de_film( const string &s )
{
  size_t fin;
  size_t debut = 0;
  if ( s.find ( "(V)" ) != string::npos )
    return string("");
  if ( s.find ( "(TV)" ) != string::npos )
    return string("");
  if ( s.find ( "(archive footage)" ) != string::npos )
    return string("");
  
  while ( s[debut] == '\t' || s[debut] == ',' )
    ++debut;

  if ( s[debut] == '"' )
    return string("");

  fin = debut;
  fin = s.find( ')', debut );
  return s.substr( debut, (fin - debut) + 1);
}

/**
 * Lis la description d'un acteur dans le stream 'is' et ajoute l'acteur lu à la
 * base de données
 *
 * - 'is' - : stream de lecture.
 * - 'lesActeurs' - Répertoire auquel sont ajoutés les acteurs.
 * - 'lesFilms'   - Répertoire auquel sont ajoutés les films.
 *
 * Retourne le nombre de lignes lues.
 */
inline
int lireActeur ( istream &is, RepertoireActeurs &lesActeurs, RepertoireFilms &lesFilms )
{
  string s, nom;
  size_t pos;
  int nb_lues;
  vector<string> les_noms_de_films;

  getline(is, s);
  nb_lues = 1;

  pos = s.find('\t');
  if ( ( pos == string::npos ) || ( pos == 0 ) )
    {
      cerr << string("Erreur en lisant le nom ") + s << endl;
      return 1;
    }

  nom = nettoyer_nom_acteur(s.substr(0, pos ));
  s = s.substr( pos, s.size() );

  while ( s.size() > 0 )
    {
      s = nettoyer_nom_de_film( s );
      if ( s.size() > 0 ) 
        {
          les_noms_de_films.push_back( s );
        }
      getline( is, s );
      ++nb_lues;
    }

  if ( les_noms_de_films.size() > 0 ) 
    {
      Film * F;
      Acteur * A = lesActeurs[ nom ];
      vector<string>::const_iterator it, it_end;
      for ( it = les_noms_de_films.begin(), it_end = les_noms_de_films.end();
           it != it_end; ++it)
        {
          F = lesFilms[ *it ];
          A->ajouteFilm( F );
          F->ajouteActeur( A );
        }
      //cout << *A << endl;
    }
  return nb_lues;
}








bool lireDonnees( const char * fichier, RepertoireActeurs & lesActeurs, RepertoireFilms & lesFilms)
{
  string s;
  ifstream lecture;
  lecture.open(fichier);
  int nb_lignes_entete;
  int ligne_fin_donnees;

  // On identifie d'abord l'entête du fichier de données et on la passe
  string commande; 
  commande  = string("grep -n -E \'THE ACTORS LIST|THE ACTRESSES LIST\' ");
  commande += string(fichier); 
  commande += string(" | cut -d: -f1");

  FILE * f = popen( commande.c_str() , "r" );
  fscanf( f, "%d", &nb_lignes_entete );
  pclose( f );
  nb_lignes_entete += 4;

  // La fin de la section de données se reconnait par une ligne composée de 77 -
  ligne_fin_donnees = 0;
  commande = string("grep -n -E \'");
  for (int i=0; i<77; ++i)
      commande += "\\-";
  commande += string("\' ") + string(fichier) + string(" | cut -d: -f1");
  f = popen( commande.c_str() , "r" );
  while (ligne_fin_donnees < nb_lignes_entete )
      fscanf( f, "%d", &ligne_fin_donnees );
  pclose( f );

  // On passe l'entête
  for (int i=0; i<nb_lignes_entete; ++i)
    getline( lecture, s );

  int nb_lignes_a_lire = ligne_fin_donnees - nb_lignes_entete - 1 ;
  int nb_lues = 0;
  Barre_Progression bp( nb_lignes_a_lire, 50 );
  cout << "Lecture du fichier : " << fichier << endl;
  while ( nb_lues < nb_lignes_a_lire )
    {
      int n;
      n = lireActeur ( lecture, lesActeurs, lesFilms ) ;
      bp.progresse(n);
      nb_lues += n;
    }
  return true;
}

