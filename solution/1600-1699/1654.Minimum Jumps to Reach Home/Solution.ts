function minimumJumps(forbidden: number[], a: number, b: number, x: number): number {
    const s: Set<number> = new Set(forbidden);
    const q: [number, number][] = [[0, 1]];
    const n = 6000;
    const vis: boolean[][] = Array.from({ length: n }, () => [false, false]);
    vis[0][1] = true;
    for (let ans = 0; q.length; ++ans) {
        for (let t = q.length; t; --t) {
            const [i, k] = q.shift()!;
            if (i === x) {
                return ans;
            }
            const nxt: [number, number][] = [[i + a, 1]];
            if (k & 1) {
                nxt.push([i - b, 0]);
            }
            for (const [j, k] of nxt) {
                if (j >= 0 && j < n && !s.has(j) && !vis[j][k]) {
                    vis[j][k] = true;
                    q.push([j, k]);
                }
            }
        }
    }
    return -1;
}
