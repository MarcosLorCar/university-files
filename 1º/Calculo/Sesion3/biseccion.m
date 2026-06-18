function [raiz] = biseccion(f, a, b, epsilon)
% Metodo de la biseccion
fa = f(a);
fb = f(b);
if fa*fb>=0
    disp("El metodo de la biseccion no aplica")
else
    while abs(b-a) >= epsilon
        [a, b] = dividir(f, a, b);
    end
    raiz = (a+b) / 2;
end