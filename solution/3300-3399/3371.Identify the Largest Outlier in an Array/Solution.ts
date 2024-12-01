function getLargestOutlier(nums: number[]): number {
    let s = 0;
    const cnt: Record<number, number> = {};
    for (const x of nums) {
        s += x;
        cnt[x] = (cnt[x] || 0) + 1;
    }
    let ans = -Infinity;
    for (const [x, v] of Object.entries(cnt)) {
        const t = s - +x;
        if (t % 2 || !cnt.hasOwnProperty((t / 2) | 0)) {
            continue;
        }
        if (+x != ((t / 2) | 0) || v > 1) {
            ans = Math.max(ans, +x);
        }
    }
    return ans;
}
