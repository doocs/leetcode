class Solution {
public:
    int diagonalSum(vector<vector<int>>& mat) {
        int n = mat.size();
        int sum = 0;

        for (int i = 0; i < n; i++) {
            
            sum = sum+ mat[i][i];

        int m =  n - 1 - i;
            if (i != m) {
                sum += mat[i][m];
            }
        }

        return sum;
    }
};

