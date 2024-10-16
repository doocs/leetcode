function minConnectedGroups(intervals: number[][], k: number): number {
    intervals.sort((a, b) => a[0] - b[0]);
    const merged: number[][] = [];
    for (const interval of intervals) {
        const [s, e] = interval;
        if (merged.length === 0 || merged.at(-1)![1] < s) {
            merged.push(interval);
        } else {
            merged.at(-1)![1] = Math.max(merged.at(-1)![1], e);
        }
    }
    const search = (x: number): number => {
        let [l, r] = [0, merged.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (merged[mid][0] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    let ans = merged.length;
    for (let i = 0; i < merged.length; ++i) {
        const j = search(merged[i][1] + k + 1);
        ans = Math.min(ans, merged.length - (j - i - 1));
    }
    return ans;
}
