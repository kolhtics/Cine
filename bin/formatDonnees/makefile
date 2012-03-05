Program_Format= formatDonnees
GCC= g++
SrcFormat= formatDonnees.cpp LecteurBD.cpp Barre_Progression.cpp
ObjFormat= formatDonnees.o FiltreBD.o Barre_Progression.o
COPT=-Wall

all: $(Program_Format)

$(Program_Format): $(ObjFormat) 
	$(GCC) $(COPT) -o $(Program_Format) $(ObjFormat) 

BD_Cinema.o: BD_Cinema.cpp BD_Cinema.h
	$(GCC) $(COPT) -c BD_Cinema.cpp

FiltreBD.o: FiltreBD.cpp FiltreBD.h
	$(GCC) $(COPT) -c FiltreBD.cpp

Barre_Progression.o: Barre_Progression.cpp Barre_Progression.h
	$(GCC) $(COPT) -c Barre_Progression.cpp


clean:
	rm -f $(ObjTest)
	rm -f $(ObjFormat)
	rm -f $(Program_Test)


