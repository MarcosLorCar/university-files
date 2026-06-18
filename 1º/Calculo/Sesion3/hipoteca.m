function [H] = hipoteca(C, i, n)
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here
H = C*(i*(i+1)^n)/((i+1)^n-1);
end