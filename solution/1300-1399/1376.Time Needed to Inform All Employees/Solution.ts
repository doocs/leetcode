function numOfMinutes(n: number, headID: number, manager: number[], informTime: number[]): number {
    const g: number[][] = new Array(n).fill(0).map(() => []);
    for (let i = 0; i < n; ++i) {
        if (manager[i] !== -1) {
            g[manager[i]].push(i);
        }
    }
    const dfs = (i: number): number => {
        let ans = 0;
        for (const j of g[i]) {
            ans = Math.max(ans, dfs(j) + informTime[i]);
        }
        return ans;
    };
    return dfs(headID);
}
