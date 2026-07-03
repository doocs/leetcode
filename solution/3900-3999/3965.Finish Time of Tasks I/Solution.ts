function finishTime(n: number, edges: number[][], baseTime: number[]): number {
    const g: number[][] = Array.from({ length: n }, () => []);

    for (const [u, v] of edges) {
        g[u].push(v);
    }

    const dfs = (i: number): number => {
        if (g[i].length === 0) {
            return baseTime[i];
        }

        let earliest = Number.MAX_SAFE_INTEGER;
        let latest = -Number.MAX_SAFE_INTEGER;

        for (const j of g[i]) {
            const a = dfs(j);
            earliest = Math.min(earliest, a);
            latest = Math.max(latest, a);
        }

        const ownDuration = latest - earliest + baseTime[i];
        return latest + ownDuration;
    };

    return dfs(0);
}
