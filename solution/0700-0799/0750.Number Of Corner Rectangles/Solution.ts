function countCornerRectangles(grid: number[][]): number {
    const n = grid[0].length;
    let ans = 0;
    const cnt: Map<number, number> = new Map();
    for (const row of grid) {
        for (let i = 0; i < n; ++i) {
            if (row[i] === 1) {
                for (let j = i + 1; j < n; ++j) {
                    if (row[j] === 1) {
                        const t = i * 200 + j;
                        ans += cnt.get(t) ?? 0;
                        cnt.set(t, (cnt.get(t) ?? 0) + 1);
                    }
                }
            }
        }
    }
    return ans;
}
