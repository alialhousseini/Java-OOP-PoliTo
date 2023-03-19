## Programmazione ad Oggetti {#programmazione-ad-oggetti style="text-align: center;"}

# Gestione File Mp3 {#gestione-file-mp3 style="text-align: center;"}

Si scriva il nucleo di un programma per la gestione di file musicali.
Tutti le classi fanno parte del package mp3.\
Scaricare il progetto Eclipse zippato da qui:
 [http://softeng.polito.it/courses/03CBI/EsameMp3.zip\
]{style="font-weight: bold;"}Importare in Eclipse il progetto Eclipse
nel file zip: NON modificare l\'interfaccia della classe
[Archivio]{style="font-style: italic;"}.\
Alla fine del compito consegnare uno zip del proprio progetto con nome
\"nome-cognome-matricola.zip\" .\
NB: i compiti consegnati che non compilano saranno penalizzati.\
Si possono usare appunti e materiale ma NON al rete e chiavi USB.

## R1. Archivi e cartelle

I file musicali sono organizzati in archivi. Un archivio, rappresentato
dalla classe Archivio, rappresenta una\
collezione di file presenti su un PC.\
Gli archivi hanno un nome, passato come parametro del costruttore e
restituito tramite il metodo getNome().\
Un archivio attinge i brani da più cartelle, che possono essere
impostate tramite il metodo aggiungiCartella() che\
riceve come parametro il path della cartella e restituisce un oggetto di
tipo Cartella. La classe Cartella offre il metodo\
getPath() per ottenere il path corrispondente.\
É possibile sapere quali sono le cartelle che costituiscono un archivio
tramite il metodo getCartelle() che restituisce\
una collezione di cartelle.\

## R2. File e Proprietà

Le cartelle contengono dei file che possono essere registrati come brani
tramite metodo aggiungiBrano() della classe\
Cartella. Tale metodo riceve come parametro il nome del file, il titolo
del brano ed il nome dell\'artista; il metodo\
restituisce un oggetto di tipo Brano.\
Dato un brano è possibile sapere la cartella di appartenenza, il nome,
il titolo e l\'artista tramite i metodi getCartella(),\
getNome(), getTitolo() e getArtista().\
Il metodo getBrani() della classe Cartella permette di ottenere la
collezione contenente tutti i brani presenti nella\
cartella.\
É possibile sapere quali sono tutti i brani di un dato artista tramite
il metodo getBraniArtista() della classe Archivio.\

## R3. Algoritmi di codifica

I brani musicali possono essere codificati usando diversi algoritmi (es.
MP3, Ogg, Wma).\
Gli algoritmi sono registrati su un archivio tramite il metodo
registraAlgoritmo() della classe Archivio che riceve\
come parametri il nome dell\'algoritmo, l\'estensione corrispondente
(senza il ".") ed il nome del programma da\
utilizzare per decodificare; questo metodo restituisce un oggetto di
tipo Algoritmo.\
La classe Algoritmo offre i metodi getNome(), getEstensione() e
getProgramma() per poter conoscere\
rispettivamente nome, estensione e programma di un algoritmo di
codifica.\
Il metodo getAlgoritmi() resituisce la collezione degli algoritmi
registrati, ordinati per estensione (ordine\
lessicografico crescente).\
Tramite il metodo getAlgoritmo() della classe Archivio deve essere
possibile ottenere l\'oggetto Algoritmo\
corrispondente ad una estensione data.\

## R4. Algoritmi dei brani

Quando viene aggiunto un brano (tramite il metodo aggiungiBrano() della
classe Cartella) il sistema deve trovare\
l\'algoritmo corrispondente in base all\'estensione del nome del file e
assegnarlo al brano.\
A posteriori, per sapere quale algoritmo sia stato assegnato ad un brano
si utilizza il metodo getAlgoritmo() della\
classe Brano.\
Per poter riprodurre il brano, si utilizza il metodo comandoPlay() della
classe Brano che restituisce la stringa con i\
comandi da eseguire. La stringa restituita è composta da due righe
separate da un a-capo ("\\n"): la prima riga contiene\
il comando "cd" seguito dal path della cartella contenente il brano, la
seconda riga contiene il nome del programma\
associato all\'algoritmo di codifica seguito dal nome del file del brano
(separati da uno spazio).\
Esempio: "cd /musica\\nmplayer one.wma"\

## R5. Lettura da file

É possibile leggere da file le informazioni relative ad archivi,
cartelle, brani, ed algoritmi tramite il metodo leggi()\
della classe Archivio.\
Le informazioni sono memorizzate una per riga, i campi di ogni riga sono
separati dal punto e virgola ";". Ogni riga\
inizia con un carattere che indica il tipo di elemento descritto.\
Gli algoritmi di codifica sono identificati dalla lettera "A" e sulla
riga sono indicati nell\'ordine nome, estensione e\
programma. Le cartelle sono identificate dalla lettera "C" e sulla riga
riportano il path della cartella.\
I brani sono caratterizzati dalla lettera "B" e sulla riga riportano
nome del file, titolo e artista. Per convenzione ogni\
brano viene aggiunto all\'ultima cartella che lo precede nel file.\
Si ignorino le righe errate.\
