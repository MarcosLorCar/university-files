function x=collatz(n)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%COLLATZ iteración de Collaz
% n: entero positivo
% x: sucesión generada por el proceso iterativo de Collatz 
%     hasta alcanzar el valor 1
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
contador=1;
while n NO IGUAL 1
	if rem(n,2)==1   	% resto de dividir n entre 2 (¿es impar?)
		n=3*n+1;
	else
		n=n/2;
	end
	contador=contador+1;
	x(count)=n;    		% almacena la iteracion actual
end

