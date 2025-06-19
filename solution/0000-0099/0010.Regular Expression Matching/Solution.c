#define MAX_LEN 1000

char *ss, *pp;
int m, n;
int f[MAX_LEN + 1][MAX_LEN + 1];

bool dfs(int i, int j) {
    if (j >= n) {
        return i == m;
    }
    if (f[i][j] != 0) {
        return f[i][j] == 1;
    }
    int res = -1;
    if (j + 1 < n && pp[j + 1] == '*') {
        if (dfs(i, j + 2) || (i < m && (ss[i] == pp[j] || pp[j] == '.') && dfs(i + 1, j))) {
            res = 1;
        }
    } else if (i < m && (ss[i] == pp[j] || pp[j] == '.') && dfs(i + 1, j + 1)) {
        res = 1;
    }
    f[i][j] = res;
    return res == 1;
}

bool isMatch(char* s, char* p) {
    ss = s;
    pp = p;
    m = strlen(s);
    n = strlen(p);
    memset(f, 0, sizeof(f));
    return dfs(0, 0);
}
