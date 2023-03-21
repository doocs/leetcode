function countDistinct(nums: number[], k: number, p: number): number {
    const n = nums.length;
    const s = new Set();
    for (let i = 0; i < n; ++i) {
        let cnt = 0;
        let t = '';
        for (let j = i; j < n; ++j) {
            if (nums[j] % p === 0 && ++cnt > k) {
                break;
            }
            t += nums[j].toString() + ',';
            s.add(t);
        }
    }
    return s.size;
}
