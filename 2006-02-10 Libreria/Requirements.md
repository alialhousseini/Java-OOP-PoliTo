## Libreria  {#libreria style="text-align: center;"}

Sviluppare un\'applicazione che consenta di gestire una libreria.\
Tutte le classi devono essere nel package \"libreria\".\

------------------------------------------------------------------------

### R1 - Editori

Il sistema viene gestito tramite un\'istanza della classe **Libreria**.\
Le librerie deveno interagice con le case editrici, rappresentate dalla
classe **Editore**; per creare un editore si utilizza il metodo
**creaEditore()** della classe Libreria che riceve come parametri il
nome dell\'editore, i tempi medi di consegna (espressi in giorni) e
l\'indirizzo email di contatto e restituisce un riferimento all\'oggetto
Editore creato.\
I parametri sono accessibili tramite il metodi getter **getNome()**,
**getTempoConsegna()** e **getEmail()** di Editore.\
È possibile ottenere un oggetto editore dato il nome tramite il metodo
**getEditore()** di Libreria.\
Il metodo **getEditori()** di Libreria restituisce l\'elenco degli
editor, ordinato alfabeticamente per nome.\

### R2 - Libri

La libreria rivende dei libri che sono rappresentati dalla class
**Libro**. Per creare un libro si utilizza il metodo **creaLibro()** che
riceve come parametri il titolo, l\'autore, l\'anno di edizione, il
prezzo e il nome dell\'editore.\
Se il nome dell\'editore non corrisponde ad alcun editore
precedentemente inserito viene generata un\'eccezione di
**EditoreInesistente**.\
I parametri sono accessibili tramite i metodi getter **getTitolo()**,
**getAutore()**, **getAnno()**, **getPrezzo()** e **getEditore()** di
Libro.\
È possibile definire e conoscere la quantità (numero di copie) di un
libro tramite i metodi **setQuantita()** e **getQuantita()**.\
È possibile ricercare un libro per autore, titolo o entrambi con il
metodo **getLibro()** che riceve come parametri l\'autore e il titolo;
uno dei due parametri può essere `null`, in tal caso la ricerca viene
effettuata solo in base all\'altro parametro. Se la ricerca risulta in
più libri ne viene restituito solo uno (a caso).\

### R3 - Vendita

Quando i libri vengono venduti per aggiornare il conteggio dei libri si
puo\' utilizzare il metodo **registraVendita()** della classe Libro che
riceve come parametro il numero della settimana (tra 1 e 52) e il mese
(1-12) in cui è stata effettuata la vendita; questo metodo decrementa la
quantità del libro e aggiorna il numero di copie venduta nella settimana
e mese.\
È possibile sapere la classifica delle vendite per una certa settimana o
mese tramite i metodi **getClassificaSettimana()** e
**getClassificaMese()** della classe Libreria che ricevono come
parametro la settimana o il mese e restituscono una collezione di libri
ordinati per numero decrescente di copie vendute (nella settimana/mese
dati).\

### R4 - Ordini

Per i libri è possibile definire una soglia di guardia (raggiunta la
quale è necessario fare un ordine) tramite il metodo **setParametri()**
di Libro, che riceve come parametri la soglia e la quantità da
riordinare.\
Quando la quantità di un libro scende ed è pari alla soglia il sistema
genera automaticamente un ordine. È possibile sapere quali sono gli
ordini effettuati tramite il metodo **getOrdini()** della classe
Libreria, che restituisce un collezione di oggetti di classe
**Ordine**.\
Dato un ordine è possibile sapere l\'editore a cui è stato spedito
tramite il metodo **getEditore()**, il libro a cui si riferisce tramite
il metodo **getLibro()**, la quantità ordinata tramite il metodo
**getQuantita()** e il numero dell\'ordine tramite il metodo
**getNumero()**. Il numero dell\'ordine è un intero assegnato
automaticamente, in maniera univoca agli ordini quando sono generati.\
È possibile sapere se è stato consegnato tramite il metodo
**isConsegnato()**, inizialmente un ordine non è consegnato. Quando
*fisicamente* vengono ricevuti i libri relativi ad un ordine si utilizza
il metodo **ordineRicevuto()** della classe Libreria, che riceve come
parametro il numero dell\'ordine; questo metodo imposta l\'ordine come
consegnato e incrementa la quantità del libro della quantità
dell\'ordine.\

### R5 - Lettura da file

Tramite il metodo **leggi()** della classe Libreria è possibile leggere
le informazioni realitive a editori e libri. Il metodo riceve come
parametro il path del file da leggere. Il file è di testo ed è
organizzato a linee con campi separati dal carattere \';\'.\
Ogni linea contiene informazioni relative a in editore o a un libro in
funzione del primo campo che può contenere la lettera \"E\" oppure \"L\"
rispettivamente.\
Le linee di descrizione degli editori contengono, dopo il primo campo, i
campi nome, tempo di consegna ed email.\
Le linee di descrizione dei libri contegono, dopo il primo campo, i
campi titolo, autore, anno, prezzo, il nome dell\'editore e la
quantità.\
Il metodo deve ignorare le linee non conformi al formato sopra
descritto.\

------------------------------------------------------------------------

\
