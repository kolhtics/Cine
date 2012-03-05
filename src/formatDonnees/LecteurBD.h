#ifndef __LECTEURBD_H__
#define __LECTEURBD_H__

#include <iostream>
#include <fstream>
#include "Barre_Progression.h"
#include "BD_Cinema.h"

/**
 * 
 */

using namespace std;


class LecteurBD {

public :

  LecteurBD( RepertoireActeurs &lesActeurs, RepertoireFilms &lesFilms );
  ~LecteurBD(){}

  void lireFichier( const char * fichier );

private:

  void lireActeur( istream & is );
  RepertoireActeurs *lesActeurs;
  RepertoireFilms *lesFilms;

};


#endif

