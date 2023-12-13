function countHighestScoreNodes(parents: number[]): number {
    const n = parents.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; i++) {
        g[parents[i]].push(i);
    }
    let [ans, mx] = [0, 0];
    const dfs = (i: number, fa: number): number => {
        let [cnt, score] = [1, 1];
        for (const j of g[i]) {
            if (j !== fa) {
                const t = dfs(j, i);
                cnt += t;
                score *= t;
            }
        }
        if (n - cnt) {
            score *= n - cnt;
        }
        if (mx < score) {
            mx = score;
            ans = 1;
        } else if (mx === score) {
            ans++;
        }
        return cnt;
    };
    dfs(0, -1);
    return ans;
}
