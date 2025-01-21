function isConsecutive(nums: number[]): boolean {
    let [mi, mx] = [nums[0], 0];
    const s = new Set<number>();
    for (const x of nums) {
        if (s.has(x)) {
            return false;
        }
        s.add(x);
        mi = Math.min(mi, x);
        mx = Math.max(mx, x);
    }
    return mx - mi + 1 === nums.length;
}
