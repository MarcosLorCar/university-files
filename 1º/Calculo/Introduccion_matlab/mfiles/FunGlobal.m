function y=FunGlobal(x)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% FunGlobal ilustra el uso de variables globales y locales 
%           en la definición  de una función
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% (1) N o m b r e:
%       FunGlobal
% (2) D a t o s    d e   e n t r a d a:
%       x variable independiente (número real)
% (3) D a t o s    d e   s a l i d a:
%       y variable idependiente (número real)
% (4) P r o c e d i m i e n t o   d e   c á l c u l o
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

global A                % variable global
c=1;                    % variable local, solo accesible dentro
                        % de la definición de la función
y=c/(x+A);
end