#!/bin/bash


RET_ARR="\010"

prepare_affichage() {
	echo -e -n "["
	for i in $( seq 1 $NB_COL )
	do
		echo -e -n " "
	done
	echo -e -n "]"
	for i in $( seq 0 $NB_COL )
	do
		echo -e -n ${RET_ARR}
	done
}



ETAT=0
OBJECTIF=$1
NB_PTS=0
NB_COL=$2

prepare_affichage

while read n
do
  ETAT=$(( $ETAT + $n ))
	while [ $NB_PTS -lt $(( ( $NB_COL * $ETAT ) / $OBJECTIF )) ] 
	do
		echo -e -n "."
		NB_PTS=$(( $NB_PTS + 1 ))
	done
done 
echo ""



