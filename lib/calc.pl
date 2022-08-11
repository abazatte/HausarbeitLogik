%
% Abdus Meisterwerk
%
%

add(X, Y, Z) :-
	Z is X + Y.
	
summe([],0).
summe([H|T],Sum) :-
	summe(T,Rest),
	Sum is H + Rest.
	
multip([],0).
multip([H],H).
multip([H|T],Prod) :-
	multip(T,Rest),
	Prod is H * Rest.

	
power(X,Y,Z) :-  
    Z is X**Y.
	
fak(0,1).
fak(N,Z) :-
       N>0,
       N1 is N-1,
       fak(N1,F1),
       Z is N * F1.
	   
modulo(X,Y,Z) :- 
    Z is mod(X,Y).
	
squareRoot(X,Z) :-
    Z is sqrt(X).
	 
division(X,Y,Z) :-
	Y \= 0,
    Z is X / Y.

multiplication(X,Y,Z) :-
    Z is X * Y.

minus(X,Y,Z) :-
    Z is X - Y.
    
cosinus(X, Z):-
	Z is cos(X).
	
sinus(X, Z):-
	Z is sin(X).
	
tan(X,Z):-
	Z is tan(X).
	 
exponential(E, Z):-
	Z is exp(E).
	
minus_list([],0).

minus_list([H|T], Sum) :-
	minus_list(T, Rest),
	Sum is H - Rest.      

ln(E,Z):-
	Z is log(E). 
	 
matrixAdd(M1, M2, M3) :- 
	maplist(maplist(sumasumarum), M1, M2, M3).

sumasumarum(X,Y,Z) :- Z is X+Y.	

matrixMinus(M1, M2, M3) :- 
	maplist(maplist(minuminus), M1, M2, M3).

minuminus(X,Y,Z) :- Z is X-Y.	


dot(V1, V2, N) :- 
    maplist(multiplication, V1, V2, P),  
    sumlist(P, N).

product(N1,N2, N3) :- 
    N3 is N1 * N2.

matrixMult(M1, M2, M3) :- 
    matrixTrans(M2, MT), 
    maplist(matrixMultHelper(MT), M1, M3).

matrixTrans([R1|Rs],T) :- findall(V,(
    nth1(Col,R1,_),
    maplist({Col}/[R,C]>>nth1(Col,R,C),[R1|Rs],V)),T).

matrixMultHelper(M2, I1, M3) :- 
   maplist(dot(I1), M2, M3).
   
