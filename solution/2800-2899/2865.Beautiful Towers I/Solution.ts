function maximumSumOfHeights(maxHeights: number[]): number {
    let ans = 0;
    const n = maxHeights.length;
    for (let i = 0; i < n; ++i) {
        const x = maxHeights[i];
        let [y, t] = [x, x];
        for (let j = i - 1; ~j; --j) {
            y = Math.min(y, maxHeights[j]);
            t += y;
        }
        y = x;
        for (let j = i + 1; j < n; ++j) {
            y = Math.min(y, maxHeights[j]);
            t += y;
        }
        ans = Math.max(ans, t);
    }
    return ans;
}
