#include <iostream>
#include <fstream>
#include <stdlib.h>
#include "FiltreBD.h"
// #include <sstream>
// #include <ctime>
// #include <map>
// #include <queue>


using namespace std;


int main(int argc, char ** argv)
{
  ifstream dataFile;

  if (argc < 2)
    {
      cerr << "Vous devez spécifier au moins un fichier de données\n" << endl;
      exit( EXIT_FAILURE );
    }

  FiltreBD filtre( cout );
  for (int i=1; i<argc; ++i)
    {
      bool b = filtre.lireFichier( argv[i] );
      if ( ! b )
        {
          cerr << "Erreur lors de la lecture de " << argv[i] << endl;
          exit ( EXIT_FAILURE );
        }
    }

  return 0;
}


