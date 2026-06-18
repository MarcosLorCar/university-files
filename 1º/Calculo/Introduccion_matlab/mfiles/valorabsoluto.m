%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%   E j e m p l o   V A L O R   A B S O L U T O 
%   Script alternativo para dibujar la gráfica del 
%   valor absoluto en [-1,1]
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

x=-1:0.1:1
y=x;
y(find(x<0))=-x(find(x<0));
plot(x,y);