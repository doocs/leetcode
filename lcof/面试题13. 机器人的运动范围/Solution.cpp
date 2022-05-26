class Solution {
public:
    int checksum(int m, int n, int target) {
        int a = 0;
        while (m > 0) {
            a += m % 10;
            m /= 10;
        }
        int b = 0;
        while (n > 0) {
            b += n % 10;
            n /= 10;
        }
        return a + b <= target;
    }
    int moving(int row, int col, vector<vector<int>>& arr, int i, int j, int target) {
        int count = 0;
        if (checksum(i, j, target) 
            && i>=0 && i < row && j>=0 && j < col
            && arr[i][j] == 0) {
            arr[i][j] = 1;
            count = 1 + moving(row, col, arr, i-1, j, target)
                    + moving(row, col, arr, i, j-1, target)
                    + moving(row, col, arr, i+1, j, target)
                    + moving(row, col, arr, i, j+1, target);
        }
        return count;
    }
    int movingCount(int m, int n, int k) {
        if (m == 0 || n == 0) {
            return 0;
        }
        vector<vector<int>> arr(m, vector<int>(n, 0));
        int cnt = moving(m, n, arr, 0, 0, k);
        return cnt;
    }
};
