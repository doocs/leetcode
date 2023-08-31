function shortestDistanceColor(colors: number[], queries: number[][]): number[] {
    const n = colors.length;
    const inf = 1 << 30;
    const right: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(3).fill(inf));
    const left: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(3).fill(-inf));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 0; j < 3; ++j) {
            right[i][j] = right[i + 1][j];
        }
        right[i][colors[i] - 1] = i;
    }
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < 3; ++j) {
            left[i][j] = left[i - 1][j];
        }
        left[i][colors[i - 1] - 1] = i - 1;
    }
    const ans: number[] = [];
    for (const [i, c] of queries) {
        const d = Math.min(i - left[i + 1][c - 1], right[i][c - 1] - i);
        ans.push(d > n ? -1 : d);
    }
    return ans;
}
