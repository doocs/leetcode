function countHighestScoreNodes(parents: number[]): number {
    const n = parents.length;
    let edge = Array.from({ length: n }, (v, i) => []);
    for (let i = 0; i < n; i++) {
        const parent = parents[i];
        if (parent != -1) {
            edge[parent].push(i);
        }
    }

    let ans = 0;
    let max = 0;
    function dfs(idx: number): number {
        let size = 1,
            score = 1;
        for (let i = 0; i < edge[idx].length; i++) {
            const child = edge[idx][i];
            let childSize = dfs(child);
            size += childSize;
            score *= childSize;
        }
        if (idx > 0) {
            score *= n - size;
        }
        if (score > max) {
            max = score;
            ans = 1;
        } else if (score == max) {
            ans++;
        }
        return size;
    }
    dfs(0);
    return ans;
}
