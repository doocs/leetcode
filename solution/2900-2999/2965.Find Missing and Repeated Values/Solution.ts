function findMissingAndRepeatedValues(grid: number[][]): number[] {
    const n = grid.length;
    const cnt: number[] = Array(n * n + 1).fill(0);
    const ans: number[] = Array(2).fill(0);
    for (const row of grid) {
        for (const x of row) {
            if (++cnt[x] === 2) {
                ans[0] = x;
            }
        }
    }
    for (let x = 1; ; ++x) {
        if (cnt[x] === 0) {
            ans[1] = x;
            return ans;
        }
    }
}
