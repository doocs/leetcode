function finishTime(n: number, edges: number[][], baseTime: number[]): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
    }
    const fin = Array(n).fill(0);
    const stk: number[][] = [[0, 0]];
    while (stk.length) {
        const [i, state] = stk.pop()!;
        if (state === 0) {
            if (g[i].length === 0) {
                fin[i] = baseTime[i];
            } else {
                stk.push([i, 1]);
                for (const j of g[i]) {
                    stk.push([j, 0]);
                }
            }
        } else {
            let earliest = Number.MAX_SAFE_INTEGER;
            let latest = -Number.MAX_SAFE_INTEGER;
            for (const j of g[i]) {
                earliest = Math.min(earliest, fin[j]);
                latest = Math.max(latest, fin[j]);
            }
            const ownDuration = latest - earliest + baseTime[i];
            fin[i] = latest + ownDuration;
        }
    }
    return fin[0];
}
