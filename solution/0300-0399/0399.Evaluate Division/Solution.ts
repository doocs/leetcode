function calcEquation(equations: string[][], values: number[], queries: string[][]): number[] {
    const g: Record<string, [string, number][]> = {};
    const ans = Array.from({ length: queries.length }, () => -1);

    for (let i = 0; i < equations.length; i++) {
        const [a, b] = equations[i];
        (g[a] ??= []).push([b, values[i]]);
        (g[b] ??= []).push([a, 1 / values[i]]);
    }

    for (let i = 0; i < queries.length; i++) {
        const [c, d] = queries[i];
        const vis = new Set<string>();
        const q: [string, number][] = [[c, 1]];

        if (!g[c] || !g[d]) continue;
        if (c === d) {
            ans[i] = 1;
            continue;
        }

        for (const [current, v] of q) {
            if (vis.has(current)) continue;
            vis.add(current);

            for (const [intermediate, multiplier] of g[current]) {
                if (vis.has(intermediate)) continue;

                if (intermediate === d) {
                    ans[i] = v * multiplier;
                    break;
                }

                q.push([intermediate, v * multiplier]);
            }

            if (ans[i] !== -1) break;
        }
    }

    return ans;
}
