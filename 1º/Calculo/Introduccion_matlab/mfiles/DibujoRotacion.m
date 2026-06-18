%--------------------------------------------------------------------------
%DibujoRotacion Este es un script que dibuja la 
%               ilustración del concepto de rotación
%--------------------------------------------------------------------------
% Define el rango de los ejes
clf %limpia la figura
%--------------------------------------------------------------------------
% El vector x contine las coordenadas x
% del punto de angulo pi/8 en la circunferencia unidad y del
% punto rotado pi/10
x=[cos(pi/8) cos(pi/8+pi/5)];
% idem para coordenada y
y=[sin(pi/8) sin(pi/8+pi/5)];
% Crea unos ejes 
h=axes('xLim',[-2,2],'Ylim',[-2,2]);
% Dibuja los dos puntos
plot(x,y,'kp','MarkerSize',7,'MarkerFaceColor','k')
% Considera los mismos ejes para el eje x y el y
axis equal
% Define un rango para los ejes [-2,2] en ambos casos
axis([-2 2 -2 2])
% Sitúa a partir de la siguiente instrucción todos los dibujos 
% en la ventana gráfica abierta
hold on
% dibuja círculo unidad
rectangle('Position',[-1 -1 2 2],'Curvature',[1,1],'LineStyle',':','LineWidth',2)
% Dibuja los ejes y pone el texto de la figura
plot([0 x(1)],[0 y(1)],'k-','LineWidth',1)
plot([0 x(2)],[0 y(2)],'k-','LineWidth',1)
title('Rotación de un punto','FontSize',13)
xlabel('Eje X','FontSize',13),ylabel('Eje Y','FontSize',13)
text(0.5,0.5,'\alpha','FontSize',15)
text(x(1)+0.1,y(1)+0.1,'(x_1,y_1)','FontSize',12)
text(x(2)+0.1,y(2)+0.1,'(x_2,y_2)','FontSize',12)
% Creacion de los ejes x e y
% crea el rango para los ejes
%figure(2)
%h=axes('Xlim',[-2,2],'Ylim',[-2,2]);
plot([-2,2],[0,0],'-'),plot([0,0],[-2,2],'-')
% Borra las marcas de los ejes h
set(h,'Xtick',[],'Ytick',[])
% Dibuja el sector circular
t=pi/8:0.005:pi/8+pi/5;
xpar=cos(t);
ypar=sin(t);
plot(xpar,ypar,'k-','LineWidth',2)

