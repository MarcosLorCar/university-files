N = 2485/22500;
M = 1125/22500;

years = 1:25;

%% Mp = 0
Mp = 0;
figure(Name="M = 0")

P = zeros(1,25);
P(1) = 25000;

for i = years(2:end)
    preP = P(i-1);
    P(i) = preP + (N - M) * preP - Mp;
end

plot(years,P)

%% Mp = 3000
Mp = 3000;
figure(Name="M = 3000")

P = zeros(1,25);
P(1) = 25000;

for i = years(2:end)
    preP = P(i-1);
    P(i) = preP + (N - M) * preP - Mp;
end

plot(years,P)

%% Mp = 0.1x
figure(Name="M = 0.1x")

P = zeros(1,25);
P(1) = 25000;

for i = years(2:end)
    preP = P(i-1);
    P(i) = preP + (N - M) * preP - (0.1 * preP);
end

plot(years,P)

%% Mp = max(0.1x, 2000)
figure(Name="M = max(0.1x, 2000)")

P = zeros(1,25);
P(1) = 25000;

for i = years(2:end)
    preP = P(i-1);
    P(i) = preP + (N - M) * preP - max(0.1 * preP, 2000);
end

plot(years,P)