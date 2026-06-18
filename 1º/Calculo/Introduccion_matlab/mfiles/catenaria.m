clear
%%%%%%%%%%%%%
%%% Catenaria
%%%%%%%%%%%%%
syms x % definición de variable en cálculo simbólico
a=17;  % Parámetro de la catenaria
b=0.0059; %Parámetro de la cantenaria
% derivada segunda de g(x)
g2=diff(sqrt(1+(a*b)^2* (sinh(b*x))^2 ),2 ); 
% derivada cuarta de g(x)
g4=diff(sqrt(1+(a*b)^2* (sinh(b*x))^2 ),4);  
% conversión de expresiones simbólicas a funciones Matlab
Dg2 = matlabFunction(g2); 
% conversión de expresiones simbólicas a funciones Matlab
Dg4 = matlabFunction(g4); 
figure(1)
subplot(1,2,1),
% Representación de la segunda derivada en [-100,100]
fplot(Dg2,[-100,100])  
title('Derivada segunda de g(x)','interpreter','latex','FontSize',12)  
subplot(1,2,2),
% Representación de la derivada cuarta  en [-100,100]
fplot(Dg4,[-100,100])  
title('Derivada cuarta de g(x)','interpreter','latex','FontSize',12)  
%Cálculo de los valores máximos tras analizar el dibujo
M2=Dg2(100)
M4=Dg4(100)
% Genera un fichero .eps con la imagen llamado fig40.eps
print -depsc2 -tiff 'fig40'

