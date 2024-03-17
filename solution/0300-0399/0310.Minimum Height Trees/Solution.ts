function findMinHeightTrees(n: number, edges: number[][]): number[] {
    if (n === 1) {
        return [0];
    }
    const g: number[][] = Array.from({ length: n }, () => []);
    const degree: number[] = Array(n).fill(0);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
        ++degree[a];
        ++degree[b];
    }
    const q: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (degree[i] === 1) {
            q.push(i);
        }
    }
    const ans: number[] = [];
    while (q.length > 0) {
        ans.length = 0;
        const t: number[] = [];
        for (const a of q) {
            ans.push(a);
            for (const b of g[a]) {
                if (--degree[b] === 1) {
                    t.push(b);
                }
            }
        }
        q.splice(0, q.length, ...t);
    }
    return ans;
}
