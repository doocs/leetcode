function longestPath(parent: number[], s: string): number {
    const n = parent.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; ++i) {
        g[parent[i]].push(i);
    }
    const down = Array(n).fill(0);
    let ans = 0;
    const stk: [number, number][] = [[0, 0]];
    while (stk.length) {
        const [i, state] = stk.pop()!;
        if (state === 0) {
            stk.push([i, 1]);
            for (const j of g[i]) {
                stk.push([j, 0]);
            }
        } else {
            let mx = 0;
            for (const j of g[i]) {
                const x = down[j] + 1;
                if (s[i] !== s[j]) {
                    ans = Math.max(ans, mx + x);
                    mx = Math.max(mx, x);
                }
            }
            down[i] = mx;
        }
    }
    return ans + 1;
}
