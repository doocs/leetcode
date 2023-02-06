#define max(a, b) (((a) > (b)) ? (a) : (b))

int maximumWealth(int** accounts, int accountsSize, int* accountsColSize) {
    int ans = INT_MIN;
    for (int i = 0; i < accountsSize; i++) {
        int sum = 0;
        for (int j = 0; j < accountsColSize[i]; j++) {
            sum += accounts[i][j];
        }
        ans = max(ans, sum);
    }
    return ans;
}
