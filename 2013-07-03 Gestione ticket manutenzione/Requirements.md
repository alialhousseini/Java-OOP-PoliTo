# Gestione Ticket Manutenzione {#gestione-ticket-manutenzione style="text-align: center;"}

\
Progettare ed implementare un programma che permetta di gestire le
rischieste di manutenzione (ticket). Tutte le classi devono appartenere
al package `ticketing`.

## R1. Utenti

La classe principale con cui interagire è **Tracker**, il cui
costruttore riceve come parametro l\'indirizzo della pagina web da cui
accedere al sistema di gestione dei ticket.\
L\'indirizzo è leggibile tramite il metodo getter **getURL()**.

Gli utenti del sistema possono essere inseriti tramite il metodo
**nuovoUtente()** che riceve come parametri il nickname, il nome reale
(opzionale, cioè può essere `null`), l\'indirizzo email e la password.\
Se il nickname dell\'utente, l\'email o la password sono `null`, il
metodo genera un\'eccezione di **InvalidInformationException**.

Dato il nickname è possibile recuperare l\'utente tramite il metodo
**getUtente()** ed è possibile conoscere l\'elenco di tutti gli utenti
tramite il metodo **getUtenti()** che restituisce la collezione degli
utenti.\

Gli utenti sono rappresentanti dalla classe **Utente** che offre i
seguenti metodi getter: **getNickname()**, **getNome()** e
**getEmail()**. Inoltre è presente il metodo **authenticate()** che
riceve come parametro la password inserita dall\'utente e restituisce
`true` se corrisponde alla password registrata, `false` altrimenti.

## R2. Prodotti

È possibile inserire un nuovo prodotto tramite il metodo
**nuovoProdotto()** che riceve come parametri il nome e la descrizione.\
Il metodo verifica che i parametri non siano nulli, nel caso genera
un\'eccezione di **InvalidInformationException**. Inoltre genera un
codice alfanumerico univoco, composto dal carattere `'P'` seguito da un
numero intero progressivo a partire da 1, che viene restituito dal
metodo.

È possibile accedere ad un prodotto dato il suo codice tramite il metodo
**getProdotto()** ed ottenere la lista dei prodotti tramite il metodo
**getProdotti()**. I prodotti sono rappresentanti dalla classe
**Prodotto** che offre i i metodi getter **getNome()**,
**getDescrizione()** e **getCodice()**.

## R3. Ticket

Quando un utente rileva un difetto di uno dei prodotti, può creare un
ticket. Quest\'operazione viene fatta tramite il metodo
**nuovoTicket()** che riceve come parametri il codice del prodotto, il
nickname dell\'utente che lo crea, e l\'etichetta.

Il metodo restituisce un oggetto di classe **Ticket** che rappresenta la
segnalazione dell\'anomalia e offre i metodi getter **getCodice()**,
**getCreatore()**, **getProdotto()**, **getEtichetta()** e
**getTimestamp()**.\
Il metodo **getCodice()** restituisce il codice del ticket che è un
numero intero progressivo, a partire da 1, assegnato automaticamente ai
ticket\
Il timestamp è un long che riporta il tempo di sistema all\'istante in
cui il ticket è stato creato, espresso in millisecondi.

È possibile ottenere un ticket dato il suo codice tramite il metodo
**getTicket()** che riceve il codice del ticket. Il metodo
**getTickets()** restituisce la lista dei ticket ordinati
cronologicamente in senso inverso, a partire dal più recente.

*Suggerimento:* il tempo di sistema è disponibile tramite il metodo
`System.currentTimeMillis()`.

## R4. Commenti

Dato un ticket è possibile aggiungere dei commenti, tramite il metodo
**nuovoCommento()** della classe Ticket che riceve come parametri il
nickname dell\'utente che inserisce il commento e il testo del commento.
Ad ogni commento viene assegnato un timestamp analogamente a quanto
fatto per i ticket.\
È possibile conosce i commenti di un ticket tramite il metodo
**getCommenti()** che restituisce la lista dei commenti ordinati
cronologicamente in senso inverso, dal più recente.

I commenti sono rappresentati dalla classe **Commento** che offre i
metodi getter **getAutore()**, **getTesto()**, **getTimestamp()** e
**getTicket()**.

## R5. Statistiche

Dato un prodotto è possibile conoscere il numero di ticket ad esso
riferiti tramite il metodo **numeroTicket()** della classe Prodotto.\
Il metodo **prodottiPerTicket()** della classe Tracker restituisce la
lista dei prodotti ordinati per numero di ticket decrescenti.
*Opzionale:* in caso di parità si ordini in modo crescente rispetto al
nome.

Dato un utente è possibile conoscere il numero di ticket da lui creati
tramite il metodo **numeroTicket()** della classe Utente.\
Il metodo **utentiPerTicket()** della classe Tracker restituisce la
lista degli utenti ordinati per numero decrescente di ticket creati.
*Opzionale:* in caso di parità si ordini in modo crescente rispetto al
nickname.

------------------------------------------------------------------------

\
