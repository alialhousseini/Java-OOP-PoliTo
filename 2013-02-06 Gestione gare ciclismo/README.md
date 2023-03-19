## Gestione gare ciclismo 

Sviluppare un programma per la gestione di gare di ciclismo. Tutte le
classi devono essere nel package `ciclismo`

### R1 - Gestione degli atleti 

L\'interazione con il sistema avviene attraverso la classe
**AssociazioneCiclismo**. È possibile inserire nuovi atleti tramite il
metodo **aggiungiAtleta()** che riceve come parametri il numero tessera
(identificatore unico di un atleta), il nome, il cognome e la società di
appartenenza.\
Deve essere possibile accedere ad un atleta dato il suo numero di
tessera tramite il metodo **getAtleta()**. Il metodo restituisce il
cognome dell\'atleta, e genera l\'eccezione **AtletaNonPresente** nel
caso la tessare non sia presente.\
Il metodo **getFullAtleta()** è del tutto analogo al precedente, ma
restituisce una stringa con numero di tessera, nome, cognome e la
società separati da virgole.\

### R2 - Gestione delle gare

È possibile inserire nuove gare tramite il metodo **aggiungiGara()** che
riceve come parametri il codice della gara (una stringa, che vale come
identificatore unico) un descrittore (stringa) e un peso (intero da 0 a
100).\
Deve essere possibile accedere ad una gara dato il suo codice tramite il
metodo **getGara()**.\
Il metodo rende il peso della gara e leva l\'eccezione
**GaraNonPresente** in caso di gara non presente.\

### R3 - Gestione classifica gara

Un atleta può essere iscritto ad una gara tramite il metodo
[aggiungiAtletaGara()]{style="font-weight: bold;"} che riceve
l\'identificatore dell\'atleta e il codice della gara.\
Il metodo leva le eccezioni AtletaNonPresente e GaraNonPresente nei
rispettivi casi.\
Il metodo **pettoraleAtletaGara()** riceve l\'identificatore
dell\'atleta e il codice della gara e rende il numero di pettorale
dell\'atleta per una certa gara, o leva l\'eccezione [NonIscritto
]{style="font-weight: bold;"}se non iscritto.\
Il numero di pettorale viene attribuito automaticamente dal programma
all\'atleta, secondo l\'ordine di iscrizione. Il primo iscritto ha
pettorale 1, il secondo 2 e cosi via.\
Allo stesso atleta sono assegnati, in generale, pettorali diversi per
ogni gara.\
Il programma gestisce la classifica di una gara come segue.\
Il metodo **tempoAtletaGara()** riceve l\'identificatore dell\'atleta,
il codice della gara e il tempo di quell\'atleta per quella gara.\
Per semplicita il tempo è dato da un int e rappresenta i minuti; inoltre
si assuma che non ci siano ex-aequo.\
Il metodo **posizioneAtletaGara()** riceve l\'identificatore
dell\'atleta, il codice della gara, e rende la posizione dell\'atleta.\
Il metodo [classificaGara()]{style="font-weight: bold;"}  riceve il
codice della gara e rende una lista con l\'ordine di arrivo per la gara,
ogni elemento è costituito da una stringa con il seguente formato:
`pettorale, codiceTessera, cognome`.\

### R4 - Gestione classifica generale

Il programma gestisce anche la classifica generale, che considera tutte
le gare elencate. La classifica viene calcolata sommando i punteggi
ottenuti per ogni gara da un atleta.\
I punteggi sono: 10 punti al primo classificato, 9 al secondo, 8 al
terzo e cosi via fino al decimo, poi zero punti.\
Il metodo **classificaGenerale()**fornisce una lista con l\'elenco degli
atleti ordinati per classifica di tutte le gare. Ogni elemento della
lista è una stringa e contiene per ogni atleta: codiceTessera, cognome,
punteggio.\
\
Esiste un altra classifica pesata. I punteggi per gara sono quelli del
metodo precedente, moltiplicati per il peso della gara\
come definito dal metodo aggiungiGara().\
Il metodo [classificaGeneralePesata()]{style="font-weight: bold;"}
fornisce questa classifica, come lista con stesso formato della
precedente\
\
Non considerare casi di ex-aequo sulla classifica di tutte le gare.\

### R5 - Lettura da file

Il metodo [loadGara()]{style="font-weight: bold;"} permette di leggere
da file i tempi di arrivo di una gara.\
Il file è strutturato a righe come segue:

-   la prima riga contiene il codice della gara
-   le righe successive riportano i tempi degli atleti, in particolare
    sono presenti nell\'ordine l\'identificatore dell\'atleta ed il
    tempo separati da un \";\".\
    I tempi sono espressi nella forma `ore:minuti:secondi`.

In caso di un errore nei dati il metodo deve terminare la lettura,
annullare tutti i tempi per la gara in corso, e levare una IOException.\
