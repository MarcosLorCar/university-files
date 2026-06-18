function [flujo] = semaforo(t)
% Función que modela el flujo permitido por el semáforo
ciclo = mod(t * 60, 5); % Convertir tiempo a minutos y obtener el residuo del ciclo de 5 minutos
    
if ciclo <= 3 % Verde o ámbar
    flujo = 3000;
else % Rojo
    flujo = 0;
end
end