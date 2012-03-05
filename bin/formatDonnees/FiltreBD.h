#ifndef __FILTREbD_H__
#define __FILTREBD_H__

#include <vector>
#include <string>
#include <iostream>
#include <fstream>
#include "Barre_Progression.h"

/**
 * 
 */

using namespace std;


class FiltreBD {

public :

  FiltreBD( ostream & os = std::cout);
  ~FiltreBD(){}

  bool lireFichier( const char * donnees );

private:

  string nettoyer_nom_acteur( const string &s);
  string nettoyer_nom_de_film( const string &s );
  int lireActeur( istream &is);

  ostream * sortie; 

};


#endif

