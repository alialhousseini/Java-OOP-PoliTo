# Magazzino  {#magazzino style="text-align: center;"}

Sviluppare l'applicazione che consente di gestire collezioni di
abbigliamento.\
Tutte le classi devono essere nel package \"abbigliamento\".\

------------------------------------------------------------------------

## R1 - Definizione Modelli 

I modelli vengono definiti tramite la clase
[Modello]{style="font-weight: bold;"}, il cui costruttore riceve come
paremetri: il nome del modello, il costo fisso per unità e la quantità
di tessuto richiesta per confezionare il modello espressa in metri
quadri.\
Deve essere possibile conoscere i valori passati al costruttore tramite
dei metodi getter: [getNome()]{style="font-weight: bold;"},
[getCosto()]{style="font-weight: bold;"},
[getQuantita()]{style="font-weight: bold;"}.\

## R2 - Definizione Materiali e Colori 

I materiali sono rappresentati dalla classe
[Materiale]{style="font-weight: bold;"}, il cui costruttore riceve come
parametri: il nome ed il costo al metro quadro. Tramite i metodi
[getNome()]{style="font-weight: bold;"} e
[getCosto()]{style="font-weight: bold;"} è possibilie conoscere nome e
costo.\
I colori sono rappresentati dalla classe
[Colore]{style="font-weight: bold;"}, il cui costruttore riceve come
parametro il nome. Il nome è accessibile tramite il metodo
[getNome()]{style="font-weight: bold;"}.\

## R3 - Definizione dei Capi 

I capi sono definiti in termini di modelli confezionati con un certo
materiale. Il costruttore della classe [Capo
]{style="font-weight: bold;"}riceve come parametri il modello, il
materiale, e il colore.\
Il metodo [prezzo()]{style="font-weight: bold;"} permette di calcolare
il prezzo di un singolo capo:\
\

::: {style="text-align: center;"}
[prezzo = costoFisso + quantitàMateriale \*
costoMateriale]{style="font-style: italic;"}\
:::

\
Deve essere possibile stampare un capo tramite il metodo
[toString()]{style="font-weight: bold;"} che restituisce una stringa
strutturata come segue:\
`<modello> <colore> di <materiale>`, ad esempio [t-shirt rosa di
cotone.]{style="font-size: 9pt; font-family: \"Courier New\";"
lang="IT"}\

## R4  - Collezione 

Una collezione è un insieme di capi ed è rappresentata dalla classe
[Collezione]{style="font-weight: bold;"}.\
E\' possibile aggiungere un capo ad una collezione tramite il metodo
[add()]{style="font-weight: bold;"}.\
La classe Collezione definisce una serie di metodi per ricercare capi
all\'interno di una collezione. Il metodo
[trova()]{style="font-weight: bold;"} viene definito in tre varianti che
hanno come parametro un Modello, un Colore, un Materiale. Il metodo
restituisce una collezione di Capi che utilizzano il modello, colore, o
materiale specificato.\
\

## R5  - Lettura da file 

È possibile leggere le informazioni da file. La lettura viene gestita
dalla classe Abbigliamento, tramite il metodo
[leggiFile()]{style="font-weight: bold;"}.\
Le informazioni sono memorizzate, un elemento per linea.\
I modelli sono definiti in linee che iniziano con MOD, seguiti da nome,
costo fisso e quantita\' di materiale, tutti separati da \";\".\
I colori sono definiti in linee che iniziano con COL, seguiti da nome,
separati da \";\".\
I materiali sono definiti in linee che iniziano con MAT, seguiti da nome
e costo del materiale, tutti separati da \";\".\
I capi sono definiti in linee che iniziano per CAP, seguiti da nome,
nome del modello, nome del materiale, e nome del colore, tutti separati
da \";\".\
Le collezioni sono descritte in linee che iniziano con COL, seguito dai
nomi dei capi tutti separati da \";\".\
\
Dopo la lettura si possono usare i metodi:
[getModello()]{style="font-weight: bold;"},
[getColore()]{style="font-weight: bold;"},
[getMateriale()]{style="font-weight: bold;"},
[getCapo()]{style="font-weight: bold;"}, e
[getCollezione()]{style="font-weight: bold;"}. Essi ricevono come
parametro il nome e restituiscono l\'elemento corrispondente.\

------------------------------------------------------------------------

\
[]{style="font-weight: bold;"}
