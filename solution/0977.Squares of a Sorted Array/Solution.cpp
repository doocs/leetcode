bool check(int x, int y){
    return abs(x) < abs(y);
}

class Solution {
public:

    vector<int> sortedSquares(vector<int>& A) {

        sort(A.begin(), A.end(), check);

        for(int i = 0; i < A.size(); i++)
            A[i] = pow(A[i], 2);

        return A;
    }
};
