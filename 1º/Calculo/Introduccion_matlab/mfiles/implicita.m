function P=implicita(t,a,k,P0)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% implicita calcula las imágenes de una función implícita  
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% (1) N o m b r e:
%       implicita
% (2) D a t o s    d e   e n t r a d a:
%           t vector de instantes de tiempo t=[t1,t2,t3,..,tn]
%           a parámetro a de la ecuación diferencial 
%           k parámetro k de la ecuación diferencial 
%           P0 proporcion inicial de pinos en el instante t=0
%(3) D a t o s    d e   s a l i d a:
%           P es un vector [P(t1) P(t2),...P(tn)] conteniendo 
%           la evolucion de las proporciones de los pinos
%(4) P r o c e d i m i e n t o   d e   c á l c u l o
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Calculamos el valor de la constante C en funcion de P0
C=1/k*(log(abs(a*P0-k))-log(abs(P0)));
% El bucle siguiente  calcula el vector de imágenes
for i=1:length(t)
    f=@(P) 1/k*(log(abs(a*P-k))-log(abs(P)))-C-t(i);
    P(i)=fzero(f,0.5); % resolución método Newton ecuación f(P)=0
end
end
