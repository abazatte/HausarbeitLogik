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
	
ln(E,Z):-
	Z is log(E).
	