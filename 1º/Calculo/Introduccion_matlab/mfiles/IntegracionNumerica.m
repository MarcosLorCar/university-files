clear
clc
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Regla de Simpson y trapecios compuesta
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

f=@(x) exp(-x) % función a integrar
a=0;           % extremo inferior
b=1;           % extremos superior
n=2;          % número de nodos
h=(b-a)/n;     % amplitud de los subintervalos
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% trapecio compuesto
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
suma_trapecios=0;
for i=1:n-1
  suma_trapecios=suma_trapecios+f(a+i*h);
end
suma_trapecios=h*(f(a)/2+f(b)/2+suma_trapecios)
format long, suma_trapecios
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Simpson compuesto
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
suma_simpson=0;
for i=1:n-1
  if(mod(i,2)==1)       % nodo impar
  suma_simpson=suma_simpson+4*f(a+i*h);
  elseif (mod(i,2)==0)  % nodo par
  suma_simpson=suma_simpson+2*f(a+i*h);
  end
end
suma_simpson=(h/3)*(f(a)+f(b)+suma_simpson)
format long, suma_simpson

  