function longestPath(parent: number[], s: string): number {
    const n = parent.length;
    let graph = Array.from({ length: n }, v => []);
    for (let i = 1; i < n; i++) {
        graph[parent[i]].push(i);
    }
    let ans = 0;
    function dfs(x: number): number {
        let maxLen = 0;
        for (let y of graph[x]) {
            let len = dfs(y) + 1;
            if (s.charAt(x) !== s.charAt(y)) {
                ans = Math.max(maxLen + len, ans);
                maxLen = Math.max(len, maxLen);
            }
        }
        return maxLen;
    }
    dfs(0);
    return ans + 1;
}
