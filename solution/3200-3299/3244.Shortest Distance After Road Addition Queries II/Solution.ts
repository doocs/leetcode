function shortestDistanceAfterQueries(n: number, queries: number[][]): number[] {
    const nxt: number[] = Array.from({ length: n - 1 }, (_, i) => i + 1);
    const ans: number[] = [];
    let cnt = n - 1;
    for (const [u, v] of queries) {
        if (nxt[u] && nxt[u] < v) {
            let i = nxt[u];
            while (i < v) {
                --cnt;
                [nxt[i], i] = [0, nxt[i]];
            }
            nxt[u] = v;
        }
        ans.push(cnt);
    }
    return ans;
}
