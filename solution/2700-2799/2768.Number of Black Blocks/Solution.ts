function countBlackBlocks(m: number, n: number, coordinates: number[][]): number[] {
    const cnt: Map<number, number> = new Map();
    const dirs: number[] = [0, 0, -1, -1, 0];
    for (const [x, y] of coordinates) {
        for (let k = 0; k < 4; ++k) {
            const [i, j] = [x + dirs[k], y + dirs[k + 1]];
            if (i >= 0 && i < m - 1 && j >= 0 && j < n - 1) {
                const key = i * n + j;
                cnt.set(key, (cnt.get(key) || 0) + 1);
            }
        }
    }
    const ans: number[] = Array(5).fill(0);
    ans[0] = (m - 1) * (n - 1);
    for (const [_, x] of cnt) {
        ++ans[x];
        --ans[0];
    }
    return ans;
}
