%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% PRACTICA4.M  Este script analiza la evolución de los pacientes
%              que han sufrido una operación de cadera
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
T = xlsread('datosmortalidad.xls')
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Dibujo de función de distribución empírica
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
F=[];       % Inicialización: función de distribución
fd=[];      % Inicialización: función de densidad de probabilidad
fdtilde3=[];% Alisamiento de medias móviles orden 3 de f.d.p.
fdtilde5=[];% Alisamiento de medias móviles orden 5 de f.d.p.

n=length(T);% Número de datos (pacientes fallecidos)
N=267;      % Número de pacientes operados
% Cálculo de función de distribución empírica
for i=1:n
    F(i)=(i-1)/N;
end
% Aproximación de la función de densidad: f(x)=F'(x)
for i=2:(n-1)
    fd(i)=(F(i+1)-F(i-1))/(T(i+1)-T(i-1));
end
fd(1)=(F(2)-F(1))/(T(2)-T(1));
fd(n)=(F(n)-F(n-1))/(T(n)-T(n-1));
% Representación de las funciones de distribución y densidad
subplot(2,2,1), plot(T,F);
xlabel('Día','FontSize',18,'FontWeight','bold','Color','r')
ylabel('Probabilidad','FontSize',18,'FontWeight',...
'bold','Color','r')
grid on
title('Funcion de distribución empírica')
subplot(2,2,3),plot(T,fd);
xlabel('Día','FontSize',18,'FontWeight','bold','Color','r')
grid on
title('Funcion de densidad empírica')
for i=2:(n-1) % A l i s a m i e n t o   o r d e n   3    %%%%%%
    fdtilde3(i)=(fd(i-1)+fd(i)+fd(i+1))/3;
end
fdtilde3(1)=(fd(1)+fd(2)+fd(3))/3;
fdtilde3(n)=(fd(n)+fd(n-1)+fd(n-2))/3;     
for i=3:(n-2) % A l i s a m i e n t o   o r d e n   5    %%%%%%
    fdtilde5(i)=(fd(i-2)+fd(i-1)+fd(i)+fd(i+1)+fd(i+2))/5;
end
fdtilde5(1)=(fd(1)+fd(2)+fd(3)+fd(4)+fd(5))/5;
fdtilde5(2)=(fd(2)+fd(3)+fd(4)+fd(5)+fd(6))/5;
fdtilde5(n-1)=(fd(n-1)+fd(n-2)+fd(n-3)+fd(n-4)+fd(n-5))/5;
fdtilde5(n)=(fd(n)+fd(n-1)+fd(n-2)+fd(n-3)+fd(n-4))/5;
% Representación gráfica de las fdp alisadas
subplot(2,2,2),plot(T,fdtilde3);
xlabel('Día','FontSize',18,'FontWeight','bold','Color','r')
grid on
title('Funcion de densidad alisada orden 3')
subplot(2,2,4),plot(T,fdtilde5);
xlabel('Día','FontSize',18,'FontWeight','bold','Color','r')
grid on
title('Funcion de densidad alisada orden 5')
save datos.mat T fd fdtilde5; % Guarda variables T,fd,fdtilde5
                              % en el archivo datos.mat



