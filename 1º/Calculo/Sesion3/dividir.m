function [anew,bnew] = dividir(f,a,b)
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
m = (a + b) / 2;
fa = f(a);
fm = f(m);
if fa*fm<0
    anew = a;
    bnew = m;
elseif fa*fm>0
    anew = m;
    bnew = b;
else
    anew = m;
    bnew = m;
end %endif

end %endfn