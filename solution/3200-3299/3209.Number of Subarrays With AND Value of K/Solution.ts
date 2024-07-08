function countSubarrays(nums: number[], k: number): number {
    let ans = 0;
    let pre = new Map<number, number>();
    for (const x of nums) {
        const cur = new Map<number, number>();
        for (const [y, v] of pre) {
            const z = x & y;
            cur.set(z, (cur.get(z) || 0) + v);
        }
        cur.set(x, (cur.get(x) || 0) + 1);
        ans += cur.get(k) || 0;
        pre = cur;
    }
    return ans;
}
