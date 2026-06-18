%% CMT con semáforo

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
n0S(j) = 0;
for i = 1:5
    n(i) = 0;
    nS(i) = 0;
end
nTotal(j) = 0;
nTotalS(j) = 0;

% Bucle principal
while t(j) <= 10
    %% Calcular la densidad de cada celda y almacenar numero total por instante
    nTotal(j) = 0;
    nTotalS(j) = 0;
    k0 = n0(j) / L;
    k0S = n0S(j) / L;
    for i = 1:5
        k(i) = n(i) / L;
        kS(i) = nS(i) / L;
        nTotal(j) = nTotal(j) + n(i);
        nTotalS(j) = nTotalS(j) + nS(i);
    end

    %% Calcular el flujo de cada celda
    % Para i = 0
    if k0 > 0
        Q0 = e(t(j)) + (k0 * L)/dt;
    else
        Q0 = e(t(j));
    end
    q0 = min(Q0, (60 * L - n(1)) / dt);
    q0S = min(Q0, (60 * L - nS(1)) / dt);

    % Para i = 1:5
    for i = 1:5
        if k(i) <= kc
            Qi = (qc / kc) * k(i);
        elseif k(i) <= kmax
            Qi = (qc / (kmax - kc)) * (kmax - k(i));
        else
            Qi = 0;
        end

        if kS(i) <= kc
            QiS = (qc / kc) * kS(i);
        elseif kS(i) <= kmax
            QiS = (qc / (kmax - kc)) * (kmax - kS(i));
        else
            QiS = 0;
        end
    
        if i < 5
            q(i) = min(Qi, (60 * L - n(i + 1)) / dt);
            qS(i) = min(QiS, (60 * L - nS(i + 1)) / dt);
        else
            q(i) = Qi;
            qS(i) = min(QiS, semaforo(t(j)));
        end
        
    end
    q5(j) = q(5);
    q5S(j) = qS(5);

    %% Calculo del siguiente numero de vehiculos
    n0(j + 1) = n0(j) + dt * (e(t(j)) - q0);
    n0S(j + 1) = n0S(j) + dt * (e(t(j)) - q0S);
    for i = 1:5
        if i == 1
            n(i) = n(i) + dt * (q0 - q(i));
            nS(i) = nS(i) + dt * (q0S - qS(i));
        else
            n(i) = n(i) + dt * (q(i - 1) - q(i));
            nS(i) = nS(i) + dt * (qS(i - 1) - qS(i));
        end
    end
    

    %% Avanzar el tiempo de la simulacion
    t(j + 1) = t(j) + dt;
    j = j + 1;
end

%% Graficar los resultados comparativos
subplot(2, 1, 1);
hold on;
plot(t(1:end-1), nTotal, 'b', 'DisplayName', 'Sin semáforo');
plot(t(1:end-1), nTotalS, 'r', 'DisplayName', 'Con semáforo');
xlabel('Tiempo (h)');
ylabel('Número total de vehículos en el arco');
legend;
title('Comparación del número de vehículos con y sin semáforo');

subplot(2, 1, 2);
hold on;
plot(t(1:end-1), q5, 'b', 'DisplayName', 'Sin semáforo');
plot(t(1:end-1), q5S, 'r', 'DisplayName', 'Con semáforo');
xlabel('Tiempo (h)');
ylabel('Flujo de salida (vehículos/h)');
legend;
title('Comparación del flujo de salida con y sin semáforo');