function isBipartite(graph: number[][]): boolean {
    const n = graph.length;
    let valid = true;
    // 0 未遍历， 1 红色标记， 2 绿色标记
    let colors = new Array(n).fill(0);
    function dfs(idx: number, color: number, graph: number[][]) {
        colors[idx] = color;
        const nextColor = 3 - color;
        for (let j of graph[idx]) {
            if (!colors[j]) {
                dfs(j, nextColor, graph);
                if (!valid) return;
            } else if (colors[j] != nextColor) {
                valid = false;
                return;
            }
        }
    }

    for (let i = 0; i < n && valid; i++) {
        if (!colors[i]) {
            dfs(i, 1, graph);
        }
    }
    return valid;
}
