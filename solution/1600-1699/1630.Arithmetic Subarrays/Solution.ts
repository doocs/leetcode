function checkArithmeticSubarrays(
    nums: number[],
    l: number[],
    r: number[],
): boolean[] {
    const check = (nums: number[], l: number, r: number): boolean => {
        const s = new Set<number>();
        const n = r - l + 1;
        let a1 = 1 << 30;
        let an = -a1;
        for (let i = l; i <= r; ++i) {
            s.add(nums[i]);
            a1 = Math.min(a1, nums[i]);
            an = Math.max(an, nums[i]);
        }
        if ((an - a1) % (n - 1) !== 0) {
            return false;
        }
        const d = Math.floor((an - a1) / (n - 1));
        for (let i = 1; i < n; ++i) {
            if (!s.has(a1 + (i - 1) * d)) {
                return false;
            }
        }
        return true;
    };
    const ans: boolean[] = [];
    for (let i = 0; i < l.length; ++i) {
        ans.push(check(nums, l[i], r[i]));
    }
    return ans;
}
