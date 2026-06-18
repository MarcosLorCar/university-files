function DibujaCollatz(n)
suc=collatz(n); % Genera la sucesión de Collatz
plot(suc,'*-')  % Dibuja la sucesión suc
title(['Sucesión de Collatz comenzando en ' int2str(suc(1))],
'FontSize'16)


% subfuncion
function x=collatz(n)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%COLLATZ iteración de Collatz
% n: entero positivo
% x: sucesión generada por el proceso iterativo de Collatz 
%    hasta alcanzar el valor 1
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
contador=1;
while n~=1
	if rem(n,2)==1   	% resto de dividir n entre 2 (¿es impar?)
		n=3*n+1;
	else
		n=n/2;
	end
	contador=contador+1;
	x(contador)=n;    		% almacena la iteracion actual
end

