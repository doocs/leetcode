function maxValue(events: number[][], k: number): number {
    events.sort((a, b) => a[0] - b[0]);
    const n = events.length;
    const f: number[][] = Array.from({ length: n }, () => Array(k + 1).fill(0));

    const dfs = (i: number, k: number): number => {
        if (i >= n || k <= 0) {
            return 0;
        }
        if (f[i][k] > 0) {
            return f[i][k];
        }

        const ed = events[i][1],
            val = events[i][2];

        let left = i + 1,
            right = n;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (events[mid][0] > ed) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        const p = left;

        f[i][k] = Math.max(dfs(i + 1, k), dfs(p, k - 1) + val);
        return f[i][k];
    };

    return dfs(0, k);
}
