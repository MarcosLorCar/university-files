% Practica de la operacion de cadera.
%% Cargamos los datos.
% T es el vector tiempo cuyas componentes nos indican el dia de
% fallecimiento del paciente
T = readmatrix("datosmortalidad.xls");
n = height(T);

%% Calculamos la funcion de distribucion empírica
F = 1:n;

for i = F
    F(i) = i/247;
end

subplot(2,2,1)
plot(T,F)
title("f. de distribucion")

%% Calculamos la funcion de densidad
fd = 1:n;

fd(1) = (F(2)-F(1))/(T(2)-T(1));
fd(n) = (F(n)-F(n-1))/(T(n)-T(n-1));

for i = 2:(n-1)
    fd(i) = (F(i+1)-F(i-1))/(T(i+1)-T(i-1));
end

subplot(2,2,3)
plot(T,fd)
title("f. de densidad")
xlabel("tiempo")

%% Calculamos con alisamiento 3
fd3 = 1:n;

fd3(1) = (fd(1)+fd(2))/2;
fd3(n) = (fd(n-1)+fd(n))/2;

for i = 2:n-1
    fd3(i) = (fd(i-1)+fd(i+1)+fd(i))/3;
end

subplot(2,2,2)
plot(T,fd3)
title("f. de densidad con alisamiento 3")
xlabel("tiempo")

%% Calculamos con alisamiento 5
fd5 = 1:n;

fd5(1) = (fd(1)+fd(2)+fd(3))/3;
fd5(2) = (fd(1)+fd(2)+fd(3)+fd(4))/4;
fd5(n) = (fd(n-2)+fd(n-1)+fd(n))/3;
fd5(n-1) = (fd(n-3)+fd(n-2)+fd(n-1)+fd(n))/4;

for i = 3:n-2
    fd5(i) = (fd(i-2)+fd(i-1)+fd(i)+fd(i+1)+fd(i+2))/5;
end

figure(2)
plot(T,fd5,'k')
title("f. de densidad con alisamiento 5")
xlabel("tiempo")