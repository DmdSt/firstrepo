{liest Art der Seminarteilnahme ein, gibt die Matr.nr. der erfolgreichen Teilnehmer aus und erstellt zum Schluss eine Liste der aktiven und eine der passiven Teilnehmer.}
{$R+} 
{$B+}

program Seminar1 (input,output);

	const 
	MAXTEILNEHMER = 12;
	
	type
	tNatZahlPlus = 1..maxint;
	tNatZahl = 0..maxint;
	tStatus = (aktiv,passiv);
	tIndex = 1..MAXTEILNEHMER;
	tMatrNrFeld = array [tIndex] of tNatZahlPlus;
	tStatusFeld = array [tIndex] of tStatus;
	
	var
	MatrNrFeld : tMatrNrFeld;
	StatusFeld : tStatusfeld;
	AnzStud : tNatZahl;
	i : tIndex;
	Status : char;
	
begin
	write('Wie viele Studenten nehmen am Seminar teil? ');
	readln(AnzStud);
	if AnzStud > MAXTEILNEHMER then
	begin
		writeln('Bitte hoechstens ',MAXTEILNEHMER,' Eingaben!');
		AnzStud:=MAXTEILNEHMER
	end;
	writeln('Geben Sie Matr.Nr. und Aktivitaet der ',AnzStud,' Teilnehmer ein:');
	
	for i:=1 to AnzStud do
	begin
		write('Matr.Nr. ');
		readln(MatrNrFeld[i]);
		write('a - aktiv, p - passiv: ');
		readln(Status);
		if Status='a' then
		StatusFeld[i]:=aktiv
		else
		StatusFeld[i]:=passiv
	end;
	
{Scheine ausgeben}
	writeln;
	for i:=1 to AnzStud do
	begin
		if StatusFeld[i]=aktiv then
		begin
		write('Der Student mit der Matr.Nr. ',MatrNrFeld[i]);
		writeln(' hat mit Erfolg am Seminar teilgenommen.');
		writeln
		end
	end;
		
	writeln('Liste aller aktiven Seminarteilnehmer');
	writeln('--------------------------------------------------');
	for i:=1 to AnzStud do
		if Statusfeld[i]=aktiv then
		writeln(MatrNrFeld[i]);
	writeln;
	
	writeln('Liste aller passiven Zuhoerer im Seminar');
	writeln('--------------------------------------------------');
	for i:=1 to AnzStud do
		if Statusfeld[i]=passiv then
		writeln(MatrNrFeld[i]);
end.
