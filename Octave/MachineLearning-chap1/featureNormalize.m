function [X_norm, mu, sigma] = featureNormalize(X)
%FEATURENORMALIZE Normalizes the features in X 
%   FEATURENORMALIZE(X) returns a normalized version of X where
%   the mean value of each feature is 0 and the standard deviation
%   is 1. This is often a good preprocessing step to do when
%   working with learning algorithms.

% You need to set these values correctly
X_norm = X;
%X_norm=[[ones(47)(:,1)],X_norm]
mu = zeros(1, size(X, 2));
sigma = zeros(1, size(X, 2));
XsSpalten=size(X_norm,2);

% ====================== YOUR CODE HERE ======================
% Instructions: First, for each feature dimension, compute the mean of the feature and subtract it from the dataset, storing the mean value in mu. Next, compute the standard deviation of each feature and divide each feature by it's standard deviation, storing the standard deviation in sigma. 
% Note that X is a matrix where each column is a feature and each row is an example. You need to perform the normalization separately for each feature. 
%
% Hint: You might find the 'mean' and 'std' functions useful.

%computing mean values and storing them in mu
for column=1:XsSpalten,
  mu(1,column)=mean(X_norm(:,column));
end;

%update X by subtracting mean value from each feature
for column=1:XsSpalten,
  X_norm(:,column)=X_norm(:,column)-mu(1,column);  
end;

%computing standard deviations and storing them in sigma
for column=1:XsSpalten,
  sigma(1,column)=std(X_norm(:,column));
end;

%Dividing X's features by standard deviations...
for column=1:XsSpalten,
  X_norm(:,column)=X_norm(:,column)./sigma(1,column);
end;

% ============================================================

end
