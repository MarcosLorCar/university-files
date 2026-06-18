%% Hito 1
datos=xlsread('EvolucionPinosMichigan.xls');
plot(datos(:,2),datos(:,3),'o--',datos(:,2),datos(:,4),'*--'); 
xlabel 'Tiempo', ylabel 'Porcentaje de Pinos'; 

%% Hito 2
tt=datos(:,2); % tiempo
Pobs=datos(:,3) %datos Pino 1
hold on
%Caso 1
a=0.05;
k=0.2;
P0=0.534;
P=implicita(tt,a,k,P0);
P=100*P;
plot(tt,P,'<-'); 
%Para el Caso 2 y Caso 3 se cambia a y k

%% Hito 3
H=@(x) SumaErrores(tt,Pobs,x(1),x(2),x(3))
x1=[0.05  0.2 0.534];
x2=[0.005 0.2 0.534];
x3=[0.005 0.1 0.534];
H(x1)
H(x2)
H(x3)

% Hito 4
[par_opt,fopt]=fminsearch(H,x1)
P=implicita(tt,par_opt(1),par_opt(2),par_opt(3))
P=100*P;
plot(tt,P,'*-');