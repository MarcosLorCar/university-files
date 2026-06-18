function H=SumaErrores(t,Pobs,a,k,P0)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% SumaErrores calcula la suma de errores al cuadrado entre 
% la solución de la ecuación diferencial y los datos observados
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% (1) N o m b r e:
%       SumaErrores
% (2) D a t o s    d e   e n t r a d a:
%           t vector de instantes de tiempo t=[t1,t2,t3,..,tn]
%           Pobs propoción de pinos observados en los instantes t
%           a parámetro a de la ecuación diferencial 
%           k parámetro k de la ecuación diferencial 
%           P0 proporcion inicial de pinos en el instante t=0
%(3) D a t o s    d e   s a l i d a:
%           H  suma de errores al cuadrado
%(4) P r o c e d i m i e n t o   d e   c á l c u l o
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
P=implicita(t,a,k,P0);  % Resolución ecuación diferencial
P=100*P;         % Conversión de las proporciones a porcentajes
H=0;             % Inicialización de variable suma de errores
for i=1:length(t)
    H=H+(P(i)-Pobs(i)).^2;
end
end