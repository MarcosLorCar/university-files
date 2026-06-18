%%%%%%%%%%%%  Hito 1 %%%%%%%%%
datos=xlsread('EvolucionPinosMichigan.xls');
plot(datos(:,2),datos(:,3),'o--g',datos(:,2),datos(:,4),'*--r'); 
xlabel 'Tiempo', ylabel 'Porcentaje de Pinos'; 

%%%%%%%%%%%%  Hito 2 %%%%%%%%%
%datos Pino 1
t=datos(:,2);
Pobs=datos(:,3)
hold on

%Caso 1
a=0.05; k=0.2; P0=0.534;
P=implicita(t,a,k,P0);
P=100*P;
plot(t,P,'<-'); 
%Caso 2
a=0.005; k=0.2; P0=0.534;
P=implicita(t,a,k,P0);
P=100*P;
plot(t,P,'>-');
%Caso 3
a=0.005; k=0.1; P0=0.534;
P=implicita(t,a,k,P0);
P=100*P;
plot(t,P,'s-');
%%%%%%%%%%%%  Hito 3 %%%%%%%%%%%%
% Caso 1
a=0.05; k=0.2; P0=0.534;
H1=SumaErrores(t,Pobs,a,k,P0)
% Caso 2
a=0.005; k=0.2; P0=0.534;
H2=SumaErrores(t,Pobs,a,k,P0)
% Caso 3
a=0.005; k=0.1; P0=0.534;
H3=SumaErrores(t,Pobs,a,k,P0)
%%%%%%%%%%%%  Hito 4 %%%%%%%%%%%%
% H=@(x) SumaErrores(tt,Pobs,x(1),x(2),x(3))
% [par_opt,fopt]=fminsearch(H,x1)
% P=implicita(tt,par_opt(1),par_opt(2),par_opt(3))
% P=100*P;
% plot(tt,P,'*-');