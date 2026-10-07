function maxKDivisibleComponents(
    n: number,
    edges: number[][],
    values: number[],
    k: number,
): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const sub: number[] = Array(n).fill(0);
    let ans = 0;
    const stk: [number, number, number][] = [[0, -1, 0]];
    while (stk.length) {
        const [i, fa, state] = stk.pop()!;
        if (state === 0) {
            stk.push([i, fa, 1]);
            for (const j of g[i]) {
                if (j !== fa) {
                    stk.push([j, i, 0]);
                }
            }
        } else {
            let s = values[i];
            for (const j of g[i]) {
                if (j !== fa) {
                    s += sub[j];
                }
            }
            if (s % k === 0) {
                ++ans;
            }
            sub[i] = s;
        }
    }
    return ans;
}
