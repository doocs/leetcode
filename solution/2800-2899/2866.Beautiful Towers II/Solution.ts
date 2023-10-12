function maximumSumOfHeights(maxHeights: number[]): number {
    const n = maxHeights.length;
    const stk: number[] = [];
    const left: number[] = Array(n).fill(-1);
    const right: number[] = Array(n).fill(n);
    for (let i = 0; i < n; ++i) {
        const x = maxHeights[i];
        while (stk.length && maxHeights[stk.at(-1)] > x) {
            stk.pop();
        }
        if (stk.length) {
            left[i] = stk.at(-1);
        }
        stk.push(i);
    }
    stk.length = 0;
    for (let i = n - 1; ~i; --i) {
        const x = maxHeights[i];
        while (stk.length && maxHeights[stk.at(-1)] >= x) {
            stk.pop();
        }
        if (stk.length) {
            right[i] = stk.at(-1);
        }
        stk.push(i);
    }
    const f: number[] = Array(n).fill(0);
    const g: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        const x = maxHeights[i];
        if (i && x >= maxHeights[i - 1]) {
            f[i] = f[i - 1] + x;
        } else {
            const j = left[i];
            f[i] = x * (i - j) + (j >= 0 ? f[j] : 0);
        }
    }
    for (let i = n - 1; ~i; --i) {
        const x = maxHeights[i];
        if (i + 1 < n && x >= maxHeights[i + 1]) {
            g[i] = g[i + 1] + x;
        } else {
            const j = right[i];
            g[i] = x * (j - i) + (j < n ? g[j] : 0);
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, f[i] + g[i] - maxHeights[i]);
    }
    return ans;
}
