N = 2485/22500;
M = 1125/22500;

years = 1:25;

%% Mp = 0
Mp = 0;
figure(Name="M = 0")

P1 = zeros(1,25);
P1(1) = 25000;
P2 = zeros(1,25);
P2(1) = 25000;

for i = years(2:end)
    preP1 = P1(i-1);
    preP2 = P2(i-1);
    % MultN = preP2 < 15000 ? preP2 / 15000 : 1;
    if preP2 < 15000
        MultN = preP2 / 15000;
    else
        MultN = 1;
    end

    if preP2 <= 30000
        MultM = 1;
    elseif preP1 <= 60000
        MultM = 1 + 2 * (preP2 - 30000) / 30000;
    else
        MultM = 3 + 4 * (preP2 - 60000) / 60000;
    end
    P1(i) = preP1 + (N - M) * preP1 - Mp;
    P2(i) = preP2 + (N * MultN - M * MultM) * preP2 - Mp;
end

subplot(1,2,1)
plot(years,P1)
subplot(1,2,2)
plot(years,P2)

%% Mp = 3000
Mp = 3000;
figure(Name="M = 3000")

P1 = zeros(1,25);
P1(1) = 25000;
P2 = zeros(1,25);
P2(1) = 25000;

for i = years(2:end)
    preP1 = P1(i-1);
    preP2 = P2(i-1);
    if preP2 < 15000
        MultN = preP2 / 15000;
    else
        MultN = 1;
    end

    if preP2 <= 30000
        MultM = 1;
    elseif preP1 <= 60000
        MultM = 1 + 2 * (preP2 - 30000) / 30000;
    else
        MultM = 3 + 4 * (preP2 - 60000) / 60000;
    end
    P1(i) = preP1 + (N - M) * preP1 - Mp;
    P2(i) = preP2 + (N * MultN - M * MultM) * preP2 - Mp;
end

subplot(1,2,1)
plot(years,P1)
subplot(1,2,2)
plot(years,P2)

%% Mp = 0.1x
figure(Name="M = 0.1x")

P1 = zeros(1,25);
P1(1) = 25000;
P2 = zeros(1,25);
P2(1) = 25000;

for i = years(2:end)
    preP1 = P1(i-1);
    preP2 = P2(i-1);
    if preP2 < 15000
        MultN = preP2 / 15000;
    else
        MultN = 1;
    end

    if preP2 <= 30000
        MultM = 1;
    elseif preP1 <= 60000
        MultM = 1 + 2 * (preP2 - 30000) / 30000;
    else
        MultM = 3 + 4 * (preP2 - 60000) / 60000;
    end
    P1(i) = preP1 + (N - M) * preP1 - (0.1 * preP1);
    P2(i) = preP2 + (N * MultN - M * MultM) * preP2 - (0.1 * preP2);
end

subplot(1,2,1)
plot(years,P1)
subplot(1,2,2)
plot(years,P2)

%% Mp = max(0.1x, 2000)
figure(Name="M = max(0.1x, 2000)")

P1 = zeros(1,25);
P1(1) = 25000;
P2 = zeros(1,25);
P2(1) = 25000;

for i = years(2:end)
    preP1 = P1(i-1);
    preP2 = P2(i-1);
    if preP2 < 15000
        MultN = preP2 / 15000;
    else
        MultN = 1;
    end

    if preP2 <= 30000
        MultM = 1;
    elseif preP1 <= 60000
        MultM = 1 + 2 * (preP2 - 30000) / 30000;
    else
        MultM = 3 + 4 * (preP2 - 60000) / 60000;
    end
    P1(i) = preP1 + (N - M) * preP1 - max(0.1 * preP1, 2000);
    P2(i) = preP2 + (N * MultN - M * MultM) * preP2 - max(0.1 * preP2, 2000);
end

subplot(1,2,1)
plot(years,P1)
subplot(1,2,2)
plot(years,P2)