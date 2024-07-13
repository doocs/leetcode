function mincostTickets(days: number[], costs: number[]): number {
    const m = days.at(-1)!;
    const f: number[] = Array(m).fill(0);
    const valid: number[] = [1, 7, 30];
    for (let i = 1, j = 0; i <= m; ++i) {
        if (i === days[j]) {
            f[i] = Infinity;
            for (let k = 0; k < 3; ++k) {
                const [c, v] = [costs[k], valid[k]];
                f[i] = Math.min(f[i], f[Math.max(0, i - v)] + c);
            }
            ++j;
        } else {
            f[i] = f[i - 1];
        }
    }
    return f[m];
}
