function plotData(x, y)
%PLOTDATA Plots the data points x and y into a new figure 
%   PLOTDATA(x,y) plots the data points and gives the figure axes labels of population and profit.

figure; % open a new figure window

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the training data into a figure using the 
%               "figure" and "plot" commands. Set the axes labels using
%               the "xlabel" and "ylabel" commands. Assume the 
%               population and revenue data have been passed in
%               as the x and y arguments of this function.

plot(x,y,'rx','Markersize',10);
ylabel('Profit in $10,000s');
xlabel('Population in City in 10,000s');
axis([4 24 -5 25]);
xticks([4:2:24]);

% ============================================================


end
