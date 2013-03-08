Use case scenario Blutzuckergehalt messen
=========================================

Nr.:                1
Szenario:           Blutzuckergehalt messen
Kurzbeschreibung:   Misst den Blutzuckergehalt des Diabetikers
Beteiligte Akteure: -
Auslöser:           Timer
Ergebnisse:         Zuckergehalt im Blut

Ablauf
------
Nr.  Wer         Was
1.1  Timer       Initiert Messung
1.2  PIP         Blutzuckergehalt messen
1.3  PIP         Ausnahme: Messgerät falsch angebracht
1.4  PIP         Timer neu setzen

Ausnahmen, Varianten
--------------------
Nr.   Wer         Was
1.3   PIP         Messgerät falsch angebracht
1.3.1 PIP         Fehlermeldung
1.3.1 PIP         Erneutes Starten des Vorgangs


Use case scenario Messung auswerten
===================================

Nr.:                2
Szenario:           Blutzuckergehaltmessung auswerten
Kurzbeschreibung:   Interpretiert den gemessenen Blutzuckergehalt unter Berücksichtugung der Kalibrierung
Beteiligte Akteure: -
Auslöser:           Use case scenario 1
Ergebnisse:         Wie viel Insulin gespritzt werden muss

Ablauf
------
Nr.  Wer         Was
2.1  PIP         Lesen der letzten Kalibrierung
2.2  PIP         Messung unter Berücksichtugung der Kalibrierung auswerten
2.3  PIP         Ausnahme: Kalibrierung hat nie stattgefunden
2.3  PIP         Entscheiden wie viel Insulin gespritzt werdne muss

Ausnahmen, Varianten
--------------------
Nr.   Wer         Was
2.3   PIP         Kalibrierung hat nie stattgefunden
2.3.1 PIP         Starten der Kalibrierung
2.3.1 PIP         Erneutes Starten des Vorgangs