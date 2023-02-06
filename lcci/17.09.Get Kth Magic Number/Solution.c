#define min(a, b) (((a) < (b)) ? (a) : (b))

int getKthMagicNumber(int k) {
    int* dp = (int*) malloc(sizeof(int) * k);
    dp[0] = 1;
    int index[3] = {0, 0, 0};
    for (int i = 1; i < k; i++) {
        int a = dp[index[0]] * 3;
        int b = dp[index[1]] * 5;
        int c = dp[index[2]] * 7;
        int num = min(a, min(b, c));
        dp[i] = num;
        if (a == num) {
            index[0]++;
        }
        if (b == num) {
            index[1]++;
        }
        if (c == num) {
            index[2]++;
        }
    }
    int res = dp[k - 1];
    free(dp);
    return res;
}
