function plotData(X, y)
% PLOTDATA Plots the data points X and y into a new figure 
% with + for the positive examples and o for the negative 
% examples. X is assumed to be a m x 2 matrix.

% Create New Figure
figure; hold on;

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the positive and negative examples on a
%               2D plot, using the option 'k+' for the positive
%               examples and 'ko' for the negative examples.
%

% Find indices of positive and negativ Examples
pos=find(y==1); neg=find(y==0);

% plot examples
plot(X(pos,1),X(pos,2),'k+','Linewidth',2,'Markersize',7);
plot(X(neg,1),X(neg,2),'ko','MarkerFaceColor','yellow');





% ==================================================================



hold off;

end
