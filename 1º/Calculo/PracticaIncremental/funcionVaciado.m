function [V, t, t75, t50, t25] = funcionVaciado(alturaContenedor, diametroContenedor, radioEspita, esCono, g, dt)
%funcionVaciado: Funcion que devuelve el tiempo que tarda en vaciarse el
%contenedor

%% Inicialización
sContenedor = (diametroContenedor/2)^2 * pi;
r = diametroContenedor / 2;
if esCono == 1
    volumen = (1/3) * pi * r * r * alturaContenedor;
else
    volumen = sContenedor * alturaContenedor;
end
V(1) = volumen;
h(1) = alturaContenedor;
k = (radioEspita^2) * pi;
t(1) = 0;

t75 = 0;
t50 = 0;
t25 = 0;

%% Bucle
i = 1;
while V(i) > 0
    % Calcular la velocidad de salida
    v = sqrt(2*g*h(i));

    % Usar la velocidad para calcular el flujo
    q = v * k;
    
    % Usar el flujo para calcular el proximo volumen
    V(i + 1) = V(i) - (q*dt);
    
    % Recalcular la altura
    % Si se le pasa esCono = 1 como parametro, se considera la formula para un cono, si es
    % cualquier otro numero, se considera un cilindro
    if esCono == 1
        h(i + 1) = ((3*V(i+1)*h(1)*h(1))/(pi*r*r)) ^ (1/3);
    else
        h(i + 1) = V(i + 1) / sContenedor;
    end

    

    % Como adición al criterio de paro, se comprueba si se ha vaciado ya el contenedor
    if V(i + 1) <= 0
        V(i + 1) = 0;
    end
    t(i + 1) = t(i) + dt;
    i = i + 1;

    % Se comprueba si se ha superado los hitos de 75, 50 y 25%
    if (t25 == 0) && (V(i) < (0.25 * V(1)))
        t25 = t(i);
    end
    if (t50 == 0) && (V(i) < (0.50 * V(1)))
        t50 = t(i);
    end
    if (t75 == 0) && (V(i) < (0.75 * V(1)))
        t75 = t(i);
    end
end


end