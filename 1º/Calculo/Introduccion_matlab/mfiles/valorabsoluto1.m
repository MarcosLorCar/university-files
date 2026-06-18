%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%   E j e m p l o   V A L O R   A B S O L U T O 
%   Este script gráfica del valor absoluto en [-1,1]
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

x=-1:0.1:1;
y=[];
n=length(x);
for i=1:21
	if (x(i) <0)
		y(i)=-x(i)
	else
		y(i)=x(i)
	end
end
plot(x,y,'-k','LineWidth',2);