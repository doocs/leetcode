function solveQueries(nums: number[], queries: number[]): number[] {
    const n = nums.length;
    const m = n * 2;
    const d: number[] = Array(m).fill(m);

    const left = new Map<number, number>();
    for (let i = 0; i < m; i++) {
        const x = nums[i % n];
        if (left.has(x)) {
            d[i] = Math.min(d[i], i - left.get(x)!);
        }
        left.set(x, i);
    }

    const right = new Map<number, number>();
    for (let i = m - 1; i >= 0; i--) {
        const x = nums[i % n];
        if (right.has(x)) {
            d[i] = Math.min(d[i], right.get(x)! - i);
        }
        right.set(x, i);
    }

    for (let i = 0; i < n; i++) {
        d[i] = Math.min(d[i], d[i + n]);
    }

    return queries.map(query => (d[query] >= n ? -1 : d[query]));
}
