%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%              MÉTODO DE NEWTON
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% s         término de la sucesión Newton  
% tol       parámetro del criterio de paro  
% f         función 
% df        derivada de la fución
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
s=4;                   % punto inicial
tol=10^(-5);           % error 
f=@(x) (3*x^2-exp(x)); % función del ejemplo
df=@(x) (6*x-exp(x));  % derivada
RES=[s];               % almacenamiento resultados
error=realmax;         % inicializa el error a "infinito"
while error>tol
snew=s-f(s)/df(s);  % Método de Newton
RES=[RES; snew];    % Almacena resultados
error=abs(snew-s);  % Calcula error
s=snew;             % 
end
display(RES)
