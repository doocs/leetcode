function maximalPathQuality(
    values: number[],
    edges: number[][],
    maxTime: number,
): number {
    const n = values.length;
    let g: Array<Array<Array<number>>> = Array.from(
        { length: n },
        v => new Array(),
    );
    for (let edge of edges) {
        let [u, v, t] = edge;
        g[u].push([v, t]);
        g[v].push([u, t]);
    }
    let visited = new Array(n).fill(false);
    let ans = 0;

    function dfs(u: number, time: number, value: number): void {
        // 索引0为终点
        if (!u) {
            ans = Math.max(value, ans);
        }
        for (let [v, dist] of g[u]) {
            if (time - dist >= 0) {
                if (!visited[v]) {
                    visited[v] = true;
                    dfs(v, time - dist, value + values[v]);
                    visited[v] = false; // 回溯
                } else {
                    dfs(v, time - dist, value);
                }
            }
        }
    }

    // 索引0为起点
    visited[0] = true;
    dfs(0, maxTime, values[0]);

    return ans;
}
