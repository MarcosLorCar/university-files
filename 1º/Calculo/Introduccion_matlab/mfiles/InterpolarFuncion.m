% Creación de una función de interpolación x-y 
close all;
mifichero=input('Nombre del fichero que contiene el mapa', 's');
open('ejemplo1.jpg')

% 
% m=100;
% 
% z=[];
% sx=[];
% sy=[];
% x=Random('Uniform',-3,3,[m,1]);
% y=Random('Uniform',-1.5,1.5,[m,1]);
% z=4*x.^2-2.1*x.^4+x.^6/3+x.*y-4*y.^2+4*y.^4;
% hold on
% plot(x,y,'.','markersize',12);
% %figure
% k=convhull(x,y);
% plot(x(k),y(k),'-r') 
% grid on
% 
% tri=delaunay(x,y);
% triplot(tri,x,y);
% %figure
% %hold off
% % superficie
% %figure
% %hidden on
% %trimesh(tri,x,y,z);
% %grid on
% %figure
% [xi,yi]=meshgrid(-3:0.1:3,-1.5:0.1:1.5);
% zi=griddata(x,y,z,xi,yi,'cubic');
% figure
% [c,h]=contour(xi,yi,zi,'r-');
% clabel(c,h);
% xlabel('eje x'), ylabel('eje y');
% 
% % % z=peaks(x,y);
% %  surf(x,y,z);
% % % figure 
% % % hidden on
% %  [xi,yi]=meshgrid(0.1:0.02:0.75);
% % zi1=interp2(x,y,z,xi,yi,'nearest');
% % zi2=interp2(x,y,z,xi,yi,'bilinear');
% % zi3=interp2(x,y,z,xi,yi,'bicubic');
% figure
% % subplot(1,3,1),surf(xi,yi,zi1);
%  surf(xi,yi,zi);
% % subplot(1,3,3),surf(xi,yi,zi3);
% % figure
% % subplot(1,3,1),contour(xi,yi,zi1);
% % subplot(1,3,2),contour(xi,yi,zi2);
% % subplot(1,3,3),contour(xi,yi,zi3);
% 
% %busqueda drl punto más cercano
% x1=-2.3;
% y1=0.;
% x2=2.5;
% y2=0;
% for j=1:100
%     i=j/100;
%     tt(j)=i;
%     xt=(1-i)*x1+i*x2;
%     sx(j)=xt;
%     yt=(1-i)*y1+i*y2;
%     sy(j)=yt;
%     
%    
% %p=dsearch(x,y,tri,xt,yt)
% %[x(p),y(p)]
% %busqueda del triangulo que lo contien
% 
% t=tsearch(x,y,tri,xt,yt);
% r=tri(t,:);
% A=[x(r),y(r)];
% B=[A'; 1 1 1];
% b=[xt yt 1];
% fz(j)=(B^(-1)*b')'*z(r)
% 
% end
% figure(1)
% hold on
% plot(sx,sy,'--g','LineWidth',2);
% figure
% plot(tt,fz,'-g','LineWidth',1);
% 
% % 
% % 
% % 
% % 
% % 
% % 
