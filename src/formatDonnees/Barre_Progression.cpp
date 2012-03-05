#include "Barre_Progression.h"


Barre_Progression::Barre_Progression( int objectif, int nb_col ) 
{
  string commande("./Barre_Progression.sh >&2 ");
  string s;
  stringstream ss;

  ss << objectif;
  ss >> s;
  commande += s + string(" ");

  s.clear();
  ss.clear();

  ss << nb_col;
  ss >> s;
  commande += s;

  sortie = popen( commande.c_str(), "w" );
}

Barre_Progression::~Barre_Progression ()
{
  pclose( sortie );
}


void Barre_Progression::progresse( int n )
{
  fprintf(sortie, "%d\n", n);
}

