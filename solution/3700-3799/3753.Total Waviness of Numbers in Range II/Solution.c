static int len, digits[20];
static long long fCnt[20][11][11][2];
static long long fWav[20][11][11][2];
static char vis[20][11][11][2];
static long long cnt, wav;

static void dfs(int pos, int prev2, int prev1, int started, int limit) {
    if (pos == len) {
        cnt = started;
        wav = 0;
        return;
    }
    if (!limit && vis[pos][prev2][prev1][started]) {
        cnt = fCnt[pos][prev2][prev1][started];
        wav = fWav[pos][prev2][prev1][started];
        return;
    }
    int up = limit ? digits[pos] : 9;
    long long c = 0, w = 0;
    for (int d = 0; d <= up; ++d) {
        int nlimit = limit && d == up;
        int ns, np2, np1, add = 0;
        if (started == 0) {
            if (d == 0) {
                ns = 0;
                np2 = 10;
                np1 = 10;
            } else {
                ns = 1;
                np2 = 10;
                np1 = d;
            }
        } else {
            ns = 1;
            np2 = prev1;
            np1 = d;
            if (prev2 != 10 && ((prev1 > prev2 && prev1 > d) || (prev1 < prev2 && prev1 < d))) {
                add = 1;
            }
        }
        dfs(pos + 1, np2, np1, ns, nlimit);
        c += cnt;
        w += wav + add * cnt;
    }
    if (!limit) {
        vis[pos][prev2][prev1][started] = 1;
        fCnt[pos][prev2][prev1][started] = c;
        fWav[pos][prev2][prev1][started] = w;
    }
    cnt = c;
    wav = w;
}

static long long calc(long long x) {
    if (x < 0) {
        return 0;
    }
    len = 0;
    if (x == 0) {
        digits[len++] = 0;
    } else {
        int buf[20];
        int l = 0;
        while (x) {
            buf[l++] = x % 10;
            x /= 10;
        }
        for (int i = l - 1; i >= 0; --i) {
            digits[len++] = buf[i];
        }
    }
    memset(vis, 0, sizeof(vis));
    dfs(0, 10, 10, 0, 1);
    return wav;
}

long long totalWaviness(long long num1, long long num2) {
    return calc(num2) - calc(num1 - 1);
}
