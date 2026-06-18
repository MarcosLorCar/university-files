function H= hipoteca(C,i,n)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%  Ejemplo de  función:  
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% (1) N o m b r e:
%       hipoteca
% (2) D a t o s    d e   e n t r a d a:
%       C capital prestado
%       i interes mensual
%       n numero de mensualidades
% (3) D a t o s    d e   s a l i d a:
%       H hipoteca en cada mensualidad 
% (4) P r o c e d i m i e n t o   d e   c á l c u l o
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

H=C*(i*(i+1)^n)/((i+1)^n-1);