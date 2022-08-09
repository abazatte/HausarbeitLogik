add(X, Y, Z) :-
	Z is X + Y.
	
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
	 
exponential(E, Z):-
	Z is exp(E).
	
minus_list([],0).

minus_list([H|T], Sum) :-
	minus_list(T, Rest),
	Sum is H - Rest.