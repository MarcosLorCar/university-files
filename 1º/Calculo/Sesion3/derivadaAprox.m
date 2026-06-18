function [y] = derivadaAprox(f, a, h)
% Funcion que devuelve la derivada de f en a
% h tiende a 0
y = (f(a+h) - f(a)) / h;
end