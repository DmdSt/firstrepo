{Gegeben ist eine lineare Liste von verschiedenen ganzen Zahlen.
Schreiben Sie eine rekursive PASCAL-Funktion, die einen Zeiger auf 
* das Listenelement mit der größten Zahl zurückliefert.
Dabei soll wie folgt vorgegangen werden:
Ist die Liste leer, wird nil zurückgegeben. Andernfalls wird beginnend
*  ab dem zweiten Listenelement rekursiv ein Zeiger auf das Listenelement
*  mit dem größten Wert bestimmt. Dieses so gefundene Maximum wird mit
*  dem Wert des ersten Listenelements verglichen. Ist das rekursiv
*  bestimmte Maximum größer als der Wert des ersten Listenelements, 
* dann wird von der Funktion ein Zeiger auf dieses Maximum, anderfalls 
* ein Zeiger auf das erste Listenelement zurückgegeben.

Beurteilen Sie, ob es sich um eine sinnvolle Anwendung der Rekursion handelt. }


program testeZeigListMax(input, output);
{ testet die Funktion ZeigListMax }

  type
  tRefListe = ^tListe;
  tListe = record
             info : integer;
             next : tRefListe
           end;

  var
  Liste,
  MaxZeig : tRefListe;
  

  function ZeigListMax (inRefAnfang : tRefListe) : tRefListe;
  { bestimmt rekursiv einen Zeiger auf das Listenelement mit
    der groessten Zahl }
   
  var
  Maxzeiger:tRefListe;
    
begin
  Maxzeiger:=inRefAnfang;

  if inRefAnfang = nil then {Fall: Liste leer}
    ZeigListMax:=nil
  else {Liste enthält Elemente}
  begin
  {Abbruchbedingung: Letztes Listenelement erreicht}
    if inRefAnfang^.next=nil then
    Maxzeiger:=inRefAnfang
    else
    begin
      Maxzeiger:=ZeigListMax(inRefAnfang^.next);
  
      if Maxzeiger^.info < ZeigListMax(inRefanfang^.next)^.info then
        Maxzeiger:=ZeigListMax(inRefAnfang^.next);
  
    end;
    
    {Angenommen bis hier stimmt es und ich hab den Maxzeiger des tails ermittelt:}
    if inRefAnfang^.info < Maxzeiger^.info then
      ZeigListMax:=Maxzeiger
    else
      ZeigListMax:=inRefAnfang;
   
  end;
  
end;





     procedure LiesListe(var outListe : tRefListe);
  { Liest eine (evtl. leere) Liste ein und gibt deren
    Anfangszeiger outListe zurueck. }

    var
    Anzahl : integer;
    i : integer;
    neueZahl : integer;
    Listenanfang,
    Listenende : tRefListe;


  begin
    Listenanfang := nil;
    repeat
      write ('Wie viele Zahlen wollen Sie eingeben? ');
      readln (Anzahl);
    until Anzahl >= 0;
 
    write ('Bitte geben Sie ', Anzahl, ' Zahlen ein: ');

    { Liste aufbauen }
    for i := 1 to Anzahl do
    begin
      read (neueZahl);
      if Listenanfang = nil then
      begin
        new (Listenanfang);
        Listenanfang^.next := nil;
        Listenanfang^.info := neueZahl;
        Listenende := Listenanfang;
      end
      else
      begin
        new (Listenende^.next);
        Listenende := Listenende^.next;
        Listenende^.next := nil;
        Listenende^.info := neueZahl
      end  { if Liste = nil }
    end; { for }
    outListe := Listenanfang;
    writeln
  end; { LiesListe }


begin 
  LiesListe (Liste);
  { Die zu testende Funktion wird zweimal aufgerufen, damit tatsaechlich
    ein Fehler auftritt, wenn die Liste durch den Aufruf zerstoert wird. }
  MaxZeig := ZeigListMax (Liste);
  MaxZeig := ZeigListMax (Liste);
  if MaxZeig = nil then
    writeln('Leere Eingabefolge!')
  else
    writeln ('Das Maximum ist ', MaxZeig^.info, '.')
end. { testeZeigListMax }
