function sse=modeloexp(parametro,T,fd)
gamma=parametro(1);
alfa=parametro(2);
beta=parametro(3);
ajustemodelo=gamma-alfa.*exp(-beta*T);
errorvector=ajustemodelo-fd;
sse=sum(errorvector.^2);
end