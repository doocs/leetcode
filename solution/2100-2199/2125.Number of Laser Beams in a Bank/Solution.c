int numberOfBeams(char** bank, int bankSize) {
    int last = 0;
    int ans = 0;
    for (int i = 0; i < bankSize; i++) {
        int t = 0;
        for (int j = 0; bank[i][j]; j++) {
            if (bank[i][j] == '1') {
                t++;
            }
        }
        if (t != 0) {
            ans += last * t;
            last = t;
        }
    }
    return ans;
}
