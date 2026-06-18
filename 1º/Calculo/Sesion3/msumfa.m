function [B] = msumfa(A,i,j,lambda)
%UNTITLED6 Summary of this function goes here
%   Detailed explanation goes here
B = A;
B(i,:) = A(j,:).*lambda + A(i,:);
end