function maxCompatibilitySum(students: number[][], mentors: number[][]): number {
    let ans = 0;
    const m = students.length;
    const vis: boolean[] = Array(m).fill(false);
    const g: number[][] = Array.from({ length: m }, () => Array(m).fill(0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < m; ++j) {
            for (let k = 0; k < students[i].length; ++k) {
                if (students[i][k] === mentors[j][k]) {
                    g[i][j]++;
                }
            }
        }
    }
    const dfs = (i: number, s: number): void => {
        if (i >= m) {
            ans = Math.max(ans, s);
            return;
        }
        for (let j = 0; j < m; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                dfs(i + 1, s + g[i][j]);
                vis[j] = false;
            }
        }
    };
    dfs(0, 0);
    return ans;
}
