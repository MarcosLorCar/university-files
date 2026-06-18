%% Simulación de un modelo CMT

% Se inician las variables
kmax = 100; % vehiculos/km
kc = 25; % vehiculos/km
L = 5; % km
dt = 1/120; % En horas
qc = 3000;
t(1) = 7;
e = @(t) 4000 * exp(-(t-8)^2);


j = 1; % variable auxiliar para el tiempo

% Inicializacion de variable de las casillas
n0(j) = 0;
for i = 1:5
    n(i) = 0;
end
nTotal(j) = 0;

% Bucle principal
while t(j) <= 10
    %% Calcular la densidad de cada celda y almacenar numero total por instante
    nTotal(j) = 0;
    k0 = n0(j) / L;
    for i = 1:5
        k(i) = n(i) / L;
        nTotal(j) = nTotal(j) + n(i);
    end

    %% Calcular el flujo de cada celda
    % Para i = 0
    if k0 > 0
        Q0 = e(t(j)) + (k0 * L)/dt;
    else
        Q0 = e(t(j));
    end
    q0 = min(Q0, (60 * L - n(1)) / dt);

    % Para i = 1:5
    for i = 1:5
        if k(i) <= kc
            Qi = (qc / kc) * k(i);
        elseif k(i) <= kmax
            Qi = (qc / (kmax - kc)) * (kmax - k(i));
        else
            Qi = 0;
        end
    
        if i < 5
            q(i) = min(Qi, (60 * L - n(i + 1)) / dt);
        else
            q(i) = Qi;
        end
        
    end
    q5(j) = q(5);

    %% Calculo del siguiente numero de vehiculos
    n0(j + 1) = n0(j) + dt * (e(t(j)) - q0);
    for i = 1:5
        if i == 1
            n(i) = n(i) + dt * (q0 - q(i));
        else
            n(i) = n(i) + dt * (q(i - 1) - q(i));
        end
    end
    

    %% Avanzar el tiempo de la simulacion
    t(j + 1) = t(j) + dt;
    j = j + 1;
end

%% Graficar los datos resultantes
subplot(2, 1, 1);
plot(t(1:end-1), nTotal);
xlabel('Tiempo (h)');
ylabel('Número total de vehículos');
title('Número de vehículos en el arco');

subplot(2, 1, 2);
plot(t(1:end-1), q5);
xlabel('Tiempo (h)');
ylabel('Flujo de salida (vehículos/h)');
title('Flujo de salida del arco');