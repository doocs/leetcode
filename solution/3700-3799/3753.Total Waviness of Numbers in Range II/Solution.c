static int len, digits[20];
static long long memoCnt[20][11][11][2];
static long long memoSum[20][11][11][2];
static char vis[20][11][11][2];
static long long cnt, sum;

static void dfs(int pos, int pp, int pr, int st, int ti) {
    if (pos == len) {
        cnt = 1;
        sum = 0;
        return;
    }
    if (!ti && vis[pos][pp][pr][st]) {
        cnt = memoCnt[pos][pp][pr][st];
        sum = memoSum[pos][pp][pr][st];
        return;
    }
    int h = ti ? digits[pos] : 9;
    long long c = 0, s = 0;
    for (int d = 0; d <= h; d++) {
        int ns = st || d;
        long long a = 0;
        int npp, np;
        if (!ns) {
            npp = 10;
            np = 10;
        } else if (!st) {
            npp = 10;
            np = d;
        } else {
            if (pp != 10 && pr != 10 && ((pr > pp && pr > d) || (pr < pp && pr < d)))
                a = 1;
            npp = pr;
            np = d;
        }
        dfs(pos + 1, npp, np, ns, ti && d == h);
        c += cnt;
        s += sum + a * cnt;
    }
    if (!ti) {
        vis[pos][pp][pr][st] = 1;
        memoCnt[pos][pp][pr][st] = c;
        memoSum[pos][pp][pr][st] = s;
    }
    cnt = c;
    sum = s;
}

static long long calc(long long N) {
    if (N < 0) return 0;
    len = 0;
    long long x = N;
    if (!x) {
        digits[len++] = 0;
    } else {
        char buf[20];
        int l = 0;
        while (x) {
            buf[l++] = x % 10;
            x /= 10;
        }
        for (int i = l - 1; i >= 0; i--)
            digits[len++] = buf[i];
    }
    memset(vis, 0, sizeof(vis));
    dfs(0, 10, 10, 0, 1);
    return sum;
}

long long totalWaviness(long long a, long long b) {
    return calc(b) - calc(a - 1);
}
