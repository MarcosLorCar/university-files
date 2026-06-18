%% HECHOS
esTipoAgua(squirtle).
esTipoFuego(charmander).
esTipoPlanta(bulbasaur).

%% REGLAS
gana(X,Y):-esTipoAgua(X),esTipoFuego(Y).
gana(X,Y):-esTipoFuego(X),esTipoPlanta(Y).
gana(X,Y):-esTipoPlanta(X),esTipoAgua(Y).