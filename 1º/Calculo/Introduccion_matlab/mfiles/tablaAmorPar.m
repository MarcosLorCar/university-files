function [T,Hnueva]= tablaAmorPar(C,i,n,k,Camor)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%  Esta función calcula la tabla de amortización de una hipoteca 
%  teniendo en cuenta que se realiza una amortización anticipada
%  de Camor euros en la k-ésima mensualidad
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% (1) N o m b r e:
%       tablaAmorPar
% (2) D a t o s    d e   e n t r a d a:
%       C capital prestado
%       i interes mensual
%       n numero de mensualidades
%       k periodo en el que se realiza la amortizacion parcial
%       Camor cantidad amortizada anticipadamente
% (3) D a t o s    d e   s a l i d a:
%       T tabla de amortización
%       Hnueva nueva hipoteca tras realizar la amortización parcial
% (4) P r o c e d i m i e n t o   d e   c á l c u l o
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

Hip=hipoteca(C,i,n); % Cálculo de la hipoteca
T(1)=C;              % En el instante inicial 
                     % se debe reintegrar todo el capital
	for j=1:n 
		if j== k			
			T(j+1)=T(j)-Hip+T(j)*i-Camor;
			Hnueva=hipoteca(T(j+1),i,(n-k));
	        Hip=Hnueva
		else
			T(j+1)=T(j)-Hip+T(j)*i;       
		end 
    end
end
		