function [J, grad] = linearRegCostFunction(X, y, theta, lambda)
% LINEARREGCOSTFUNCTION Compute cost and gradient for regularized 
% linear regression with multiple variables
% [J, grad] = LINEARREGCOSTFUNCTION(X, y, theta, lambda) computes 
% the cost of using theta as the parameter for linear regression 
% to fit the data points in X and y. Returns the cost in J and 
% the gradient in grad

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost and gradient of regularized 
% linear regression for a particular choice of theta.
%
% You should set J to the cost and grad to the gradient.
%

hyp=X*theta;

% theta_0 should not be regularized (in costfunction or gradient)
thetarest=[0;theta(2:end)];

% costfunction for linear regression
cost = ((hyp-y)'*(hyp-y))/(2*m);

% regularization to add to costfunction
reg = (lambda*thetarest'*thetarest)/(2*m);

% regularized lin. regression cost function J
J = cost + reg;

% regularized lin. regression gradient
grad =(X'*(hyp-y))/m + (lambda*thetarest)/m;

grad = grad(:);

end




% ===============================================================




