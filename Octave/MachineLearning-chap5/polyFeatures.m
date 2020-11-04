function [X_poly] = polyFeatures(X, p)
% POLYFEATURES Maps X (1D vector) into the p-th power.
% [X_poly] = POLYFEATURES(X, p) takes a data matrix X 
% (size m x 1) and maps each example into its polynomial features 
% where X_poly(i, :) = [X(i) X(i).^2 X(i).^3 ...  X(i).^p];
%


% You need to return the following variables correctly.
X_poly = zeros(numel(X),p);
X_addon=zeros(numel(X),p);

% ====================== YOUR CODE HERE ======================
% Instructions: Given a vector X, return a matrix X_poly where 
% the p-th column of X contains the values of X to the p-th 
% power.
%


for i=1:size(X,1),
  xpowers=0;
  for j=2:p,
    %newpoly=X(i)^j;
    xpowers=[xpowers,[X(i)^j]];
  end;
  X_addon(i,:)=xpowers;
end;

X_poly=[X,[X_addon(:,2:end)]];

% ===============================================================

end








