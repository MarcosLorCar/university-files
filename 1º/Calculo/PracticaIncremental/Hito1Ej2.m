%% Depósito Cónico
figure("Name", "Depósito Cónico")

altura = 10;
diametro = 5;
rEspita = 0.015; % 1.5 cm = 1.5 * 10^-2 m
g = 9.80665;
dt = 60; % 1 minuto
esCono = 1; % Es un cono

[V, T, t75, t50, t25] = funcionVaciado(altura, diametro, rEspita, esCono, g, dt);
plot(T / 60, V)
grid("on") % Solo para mejor visual
xlabel("Tiempo en minutos")
ylabel("Volumen en m^3")
disp("Está al 75% a los " + t75 / 60 + " minutos")
disp("Está al 50% a los " + t50 / 60 + " minutos")
disp("Está al 25% a los " + t25 / 60 + " minutos")