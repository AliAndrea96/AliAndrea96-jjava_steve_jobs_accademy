Sistema di Prenotazione Autobus
Descrizione
Questa applicazione Java Swing simula un sistema di prenotazione autobus, gestendo l'aggiunta e la rimozione di passeggeri e autisti. Il progetto è sviluppato seguendo i principi della programmazione orientata agli oggetti, con utilizzo di interfacce, eccezioni personalizzate e gestione degli eventi Swing.

Funzionalità Principali
Gestione Posti: L'autobus supporta 20 passeggeri e 2 autisti, con un contatore dedicato per i 2 posti disabili. I posti disponibili sono aggiornati dinamicamente.

Aggiunta e Rimozione Utenti: Permette l'inserimento di passeggeri e autisti con gestione specifica di attributi (es. destinazione, bagaglio per passeggeri; patente, anni di esperienza per autisti). La rimozione aggiorna i posti disponibili.

Validazione Input: Implementa la validazione del codice fiscale (16 caratteri alfanumerici) e altri campi per garantire l'integrità dei dati.

Eccezioni Personalizzate: Utilizza PostiEsauritiPasseggeroException e PostiEsauritiAutistaException per segnalare l'esaurimento dei posti.

Struttura Dati: Gli utenti sono gestiti in una ArrayList. La gerarchia include l'interfaccia UtenteAutobus (con getTipo()) estesa da Passeggero e Autista.

Interfaccia Utente: Presenta un Look and Feel "Nimbus" moderno e un UtenteCellRenderer per una visualizzazione chiara nelle liste.

Nota Aggiuntiva
L'applicazione non persiste i dati dopo la chiusura; ogni avvio ripristina i contatori dei posti e le liste.

Requisiti e Avvio
Requisiti: JDK 8 o superiore.

Avvio: Eseguire la classe PrenotazioneAutobusGUI all'interno del package Prenotazione_Autobus.