#define max(a, b) (((a) > (b)) ? (a) : (b))

int longestContinuousSubstring(char* s) {
    int n = strlen(s);
    int ans = 1, cnt = 1;
    for (int i = 1; i < n; ++i) {
        if (s[i] - s[i - 1] == 1) {
            ++cnt;
            ans = max(ans, cnt);
        } else {
            cnt = 1;
        }
    }
    return ans;
}
