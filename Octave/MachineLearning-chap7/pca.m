function [U, S] = pca(X)
% PCA Run principal component analysis on the dataset X
% [U, S, X] = pca(X) computes eigenvectors of the covariance 
% matrix of X, returns the eigenvectors U, the eigenvalues 
% (on diagonal) in S.
%

% Useful values
[m, n] = size(X);

% You need to return the following variables correctly.
U = zeros(n);
S = zeros(n);

% ==================== YOUR CODE HERE ======================
% Instructions: You should first compute the covariance 
% matrix. Then, you should use the "svd" function to compute 
% the eigenvectors and eigenvalues of the covariance matrix. 
%
% Note: When computing the covariance matrix, remember to 
% divide by m (the number of examples).
%

Sigma = (X'*X)/m;  % Sigma is the covariance matrix' name
[U, S, V] = svd(Sigma); % svd = singular value decomposition




% ===============================================================

end
