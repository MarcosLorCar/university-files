%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%   E j e m p l o   d e    M f i l e 
%   Este fichero es un script que dibuja la funcion 
%   seno y coseno en el intervalo [-pi,pi]
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% El signo % (porcentaje) permite introducir comentarios
% Generamos valores de la variable independiente x
% en el intervalo [-pi,pi] espaciados 0.1 unidades
x=-pi:0.01:pi;
% calculamos las imagenes del seno y coseno y lo almacenamos en el
% vector yseno e ycoseno
yseno=sin(x);
ycoseno=cos(x);
% Dibujamos dos figuras.  Una contiene la gráfica de la función 
% seno y la otra de la función coseno. Las gráficas se dibujan en
% negro, con un grosor de 2 unidades y los puntos unidos.
subplot(1,2,1),plot(x,yseno,'k-','LineWidth',2)
subplot(1,2,2),plot(x,ycoseno,'k-','LineWidth',2)