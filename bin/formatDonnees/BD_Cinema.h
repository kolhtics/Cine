#ifndef __BDCINEMA_H__
#define __BDCINEMA_H__

#include <set>
#include <map>
#include <vector>
#include <string>
#include <iostream>
#include <fstream>
#include "Barre_Progression.h"

using namespace std;

class Film;
class Acteur;

typedef set<const Acteur *> EnsActeurs;
typedef set<const Film *> EnsFilms;


template<typename T>
class Repertoire {

public :

  Repertoire(){}
  ~Repertoire(){}

  /**
   * Permet d'ajouter et/ou consulter une entrée du répertoire
   */
  T * operator[](const string & s);

  /**
   * Retourne la taille du répertoire
   */
  size_t size() const { return lesObjets.size(); }

  /**
   * Recherche un élément
   */
  T * find(const string & s);

  /**
   * Recherche un élément constant
   */
  const T * find(const string & s) const;

private :

  typedef map< string, T> Conteneur;
  typedef typename map< string, T>::iterator iterator;
  typedef typename map< string, T>::const_iterator const_iterator;
  Conteneur lesObjets;

};

typedef Repertoire<Film> RepertoireFilms;
typedef Repertoire<Acteur> RepertoireActeurs;

class Acteur {

public:

  // Constructeurs vide
  Acteur() { nom = string(""); }

  // Constructeur à partir d'une chaine de caractère donnant le nom de l'acteur.
  Acteur(string n) { nom = n; }


  // Constructeur de copie.
  Acteur(const Acteur & other)
    { 
      nom = other.nom;
      EnsFilms::const_iterator i = other.begin();
      while (i != other.end()) {
          films.insert(*(i++));
      }
    }

  //Destructeur, ne fait rien.
  ~Acteur(){}

  //Opérateur de copie.
  Acteur & operator=(const Acteur & other) 
    {
      nom = other.nom;
      EnsFilms::const_iterator i = other.begin();
      while (i != other.end()) {
          films.insert(*(i++));
      }
      return *this;
    }

  // getteur
  const string & getNom() const { return nom; }

  // Outils d'itération
  typedef EnsFilms::const_iterator const_iterator;
  const_iterator begin() const { return films.begin(); }
  const_iterator end() const { return films.end(); }

  // Recherche d'un film parmis la liste de l'acteur
  const_iterator find(const Film * s) const { return films.find(s); }

  // Fonction de comparaison, nécessaire pour insérer les acteurs dans un map.
  bool operator< (const Acteur & other) { return (nom.compare(other.nom) < 0); }

  // Ajoute un film à la liste de films de l'acteur.
  void ajouteFilm(const Film * m) { films.insert(m);}

  // Détermine si cet acteur et 'other' ont joué dans un même film.
  // Si oui, un pointeur vers un films où les deux apparaisent est retourné.
  const Film * filmEnCommun(const Acteur & other) const;

  // Opérateur pour envoyer dans un flux d'affichage.
  friend ostream & operator<< (ostream & os, const Acteur & a);

private :

  // La liste des films
  EnsFilms films;

  // Le nom de l'acteur
  string nom;


};



class Film {

public:


  // Constructeur par défaut, ne fair rien.
  Film() {}


  // Constructeur à partir d'une chaine de caractère donnant le titre du film.
  Film(string n)
    { 
      titre = n;
    }


  // Constructeur de copie.
  Film(const Film & other)
    { 
      titre = other.titre;
      EnsActeurs::const_iterator i = other.begin();
      while (i != other.end()) {
          acteurs.insert(*(i++));
      }
    }

  // Destructeur, ne fait rien.
  ~Film(){}

  // Operateur de copie.
  Film & operator=(const Film & other) 
    {
      titre = other.titre;
      EnsActeurs::const_iterator i = other.begin();
      while (i != other.end()) {
          acteurs.insert(*(i++));
      }
      return *this;
    }

  // Getteur qui retourne le titre.
  const string & getTitre() const
    {
      return titre;
    }

  // Outils d'itération
  typedef EnsActeurs::const_iterator const_iterator;
  const_iterator begin() const { return acteurs.begin(); }
  const_iterator end() const { return acteurs.end(); }

  // Recherche d'un acteur parmi ceux du film.
  const_iterator find(const Acteur * a) const { return acteurs.find(a); }

  // Operateur de comparaison, nécessaire pour insérer les films dans un map.
  bool operator< (const Film & other)
    {
      return (titre.compare(other.titre) < 0);
    }

  // Détermine si ce film et 'other' ont au moins un acteur en commun.
  // Si oui, retourne un pointeur vers l'un d'eux.
  const Acteur * acteurEnCommun(const Film & other) const;

  // Ajoute un acteur à la liste du film.
  void ajouteActeur(const Acteur *  a) { acteurs.insert(a); }

  // Opérateur pour envoyer dans un flux d'affichage.
  friend ostream & operator<< (ostream & os, const Film & a);

private :

  EnsActeurs acteurs;
  string titre;
};



template<typename T>
T * Repertoire<T>::operator[](const string & s)
{
  T * a = find(s);
  if (a == NULL) {
      a = &(lesObjets[s] = T(s));
  }
  return a;
}

template<typename T>
T * Repertoire<T>::find(const string & s)
{
  T * a = NULL;
  iterator i = lesObjets.find(s);
  if (i != lesObjets.end()) 
    {
      a =  &((*i).second);
    }
  return a;
}

template<typename T>
const T * Repertoire<T>::find(const string & s) const
{
  const T * a = NULL;
  const_iterator i = lesObjets.find(s);
  if (i != lesObjets.end()) 
    {
      a =  &((*i).second);
    }
  return a;
}
#endif

