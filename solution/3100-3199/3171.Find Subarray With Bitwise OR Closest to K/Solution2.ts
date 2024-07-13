function minimumDifference(nums: number[], k: number): number {
    let ans = Infinity;
    let pre = new Set<number>();
    for (const x of nums) {
        const cur = new Set<number>();
        cur.add(x);
        for (const y of pre) {
            cur.add(x | y);
        }
        for (const y of cur) {
            ans = Math.min(ans, Math.abs(y - k));
        }
        pre = cur;
    }
    return ans;
}
