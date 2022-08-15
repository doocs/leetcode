function smallestNumber(pattern: string): string {
    const n = pattern.length;
    const res = new Array(n + 1).fill('');
    const vis = new Array(n + 1).fill(false);
    const dfs = (i: number, num: number) => {
        if (i === n) {
            return;
        }

        if (vis[num]) {
            vis[num] = false;
            if (pattern[i] === 'I') {
                dfs(i - 1, num - 1);
            } else {
                dfs(i - 1, num + 1);
            }
            return;
        }

        vis[num] = true;
        res[i] = num;

        if (pattern[i] === 'I') {
            for (let j = res[i] + 1; j <= n + 1; j++) {
                if (!vis[j]) {
                    dfs(i + 1, j);
                    return;
                }
            }
            vis[num] = false;
            dfs(i, num - 1);
        } else {
            for (let j = res[i] - 1; j > 0; j--) {
                if (!vis[j]) {
                    dfs(i + 1, j);
                    return;
                }
            }
            vis[num] = false;
            dfs(i, num + 1);
        }
    };
    dfs(0, 1);
    for (let i = 1; i <= n + 1; i++) {
        if (!vis[i]) {
            res[n] = i;
            break;
        }
    }

    return res.join('');
}
