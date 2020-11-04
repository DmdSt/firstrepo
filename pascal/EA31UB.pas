{Schreiben Sie eine PASCAL-Funktion, welche die zweitgrößte Zahl in einem Feld von mehreren integer-Zahlen bestimmt. Sie können davon ausgehen, daß alle integer-Zahlen verschieden sind.

Sie sollen dazu eine Vorgehensweise anwenden, die der Vorgehensweise zum Bestimmen des Maximums in einem Feld sehr ähnlich ist. Insbesondere darf inFeld nur einmal durchlaufen und nicht verändert werden.

Hinweis: Während man sich in der Schleife für die Maximumbestimmung das bisher gefundene Maximum in einer Variablen merkt, legt man sich jetzt in zwei Variablen die bisher gefundene größte und
 zweitgrößte Zahl ab. }





program testeFeldZweitMax (input, output);
{ testet die Funktion FeldZweitMax }

  const
  FELDGROESSE = 4;

  type
  tIndex = 1..FELDGROESSE;
  tFeld = array [tIndex] of integer;

  var 
  Feld : tFeld;
  i : integer;

  function FeldZweitMax (var inFeld : tFeld) : integer;

    var
    max,
    zweitmax: integer;
    Feldgros, 
    j: tIndex;
    

  begin 
  Feldgros:=FELDGROESSE;
{ Bedingung: Feldgroesse im Hauptprogramm betraegt mind. 1}
  

max:=inFeld[1];
zweitmax:=inFeld[1];

for j:=1 to Feldgros do
begin

if inFeld[j] > max then
begin
zweitmax:=max;
max:=inFeld[j]
end
else
if (inFeld[j] > zweitmax) or (zweitmax=max) then
zweitmax:=inFeld[j];





end;
FeldZweitMax:=zweitmax;
end;









begin { Testprogramm }
  writeln('Bitte geben Sie ', FELDGROESSE, ' Zahlen ein:');
  for i := 1 to FELDGROESSE do
    read (Feld [i]);
  writeln('Die zweitgroesste Zahl ist ', FeldZweitMax (Feld), '.');
end. { testeFeldZweitMax }

  
