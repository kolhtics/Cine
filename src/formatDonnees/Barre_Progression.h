#ifndef __BARREPROGRESSION_H__
#define __BARREPROGRESSION_H__

#include <stdlib.h>
#include <iostream>
#include <string>
#include <sstream>

#include <fstream>

using namespace std;

class Barre_Progression {

public :

  Barre_Progression( int objectif, int nb_col = 50 );
  ~Barre_Progression ();

  void progresse( int n = 1 );

private :
  int valeur_max;
  int nb_col_total;
  FILE * sortie;
};

#endif

