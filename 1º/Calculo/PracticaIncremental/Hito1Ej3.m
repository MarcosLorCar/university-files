%% En la luna
figure("Name", "En la luna")

altura = 10;
diametro = 5;
rEspita = 0.038; % 3.8 cm = 3.8 * 10^-2 m
gTierra = 9.80665;
gLuna =  1.6;
dt = 60; % 1 minuto

[VCilintroTierra, TCilindroTierra] = funcionVaciado(altura, diametro, rEspita, 0, gTierra, dt);
[VConoTierra, TConoTierra] = funcionVaciado(altura, diametro, rEspita, 1, gTierra, dt);
[VCilintroLuna, TCilindroLuna] = funcionVaciado(altura, diametro, rEspita, 0, gLuna, dt);
[VConoLuna, TConoLuna] = funcionVaciado(altura, diametro, rEspita, 1, gLuna, dt);

%% Cilindro en la tierra
subplot(2,2,1)
plot(TCilindroTierra / 60, VCilintroTierra)
title("Cilindro en la tierra")
xlabel("Tiempo en minutos")
ylabel("Volumen en m^3")

%% Cono en la tierra
subplot(2,2,2)
plot(TConoTierra / 60, VConoTierra)
title("Cono en la tierra")
xlabel("Tiempo en minutos")
ylabel("Volumen en m^3")

%% Cilindro en la luna
subplot(2,2,3)
plot(TCilindroLuna / 60, VCilintroLuna)
title("Cilindro en la luna")
xlabel("Tiempo en minutos")
ylabel("Volumen en m^3")

%% Cono en la luna
subplot(2,2,4)
plot(TConoLuna / 60, VConoLuna)
title("Cono en la luna")
xlabel("Tiempo en minutos")
ylabel("Volumen en m^3")