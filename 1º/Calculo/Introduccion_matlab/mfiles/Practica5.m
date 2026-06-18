%%%%%%%%%%%%%%%%%%%%%%    H i t o   1     %%%%%%%%%%%%%%%%%%%% 
datos=xlsread('EvolucionPinosMichigan.xls');
plot(datos(:,2),datos(:,3),'o--g',datos(:,2),datos(:,4),'*--r'); 
xlabel 'Tiempo', ylabel 'Porcentaje de Pinos';
%%%%%%%%%%%%%%%%%%%%%%    H i t o   2     %%%%%%%%%%%%%%%%%%%% 
t=datos(:,2);
Pobs=datos(:,3);        % Datos evolución Pino 1
hold on
%Caso 1
a=0.05; k=0.2; P0=0.534;
P=implicita(t,a,k,P0); % Evolución pinos para el Caso 1
P=100*P;               % Conversión proporciones a porcentajes
plot(t,P,'<-'); 
%Caso 2
a=0.005; k=0.2; P0=0.534;
P=implicita(t,a,k,P0);% Evolución pinos para el Caso 2
P=100*P;
plot(t,P,'>-');
%Caso 3
a=0.005; k=0.1; P0=0.534;
P=implicita(t,a,k,P0); % Evolución pinos para el Caso 1
P=100*P;
plot(t,P,'s-');
%%%%%%%%%%%%%%%%%%%%%%    H i t o   3     %%%%%%%%%%%%%%%%%%%% 
a=0.05; k=0.2; P0=0.534;        % Caso 1
H1=SumaErrores(t,Pobs,a,k,P0)
a=0.005; k=0.2; P0=0.534;       % Caso 2
H2=SumaErrores(t,Pobs,a,k,P0)
a=0.005; k=0.1; P0=0.534;       % Caso 3
H3=SumaErrores(t,Pobs,a,k,P0)
%%%%%%%%%%%%%%%%%%%%%%    H i t o   4     %%%%%%%%%%%%%%%%%%%% 
H=@(x) SumaErrores(t,Pobs,x(1),x(2),x(3)); % Criterio de ajuste
x1=[a,k,P0]; % Parámetros iniciales
[par_opt,fopt]=fminsearch(H,x1); % Optimización criterio de ajuste
% Representación de la solución encontrada
P=implicita(t,par_opt(1),par_opt(2),par_opt(3)); 
P=100*P;
plot(t,P,'*-k','LineWidth',2);