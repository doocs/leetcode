function subsetsWithDup(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const ans: number[][] = [];
    for (let mask = 0; mask < 1 << n; ++mask) {
        const t: number[] = [];
        let ok: boolean = true;
        for (let i = 0; i < n; ++i) {
            if (((mask >> i) & 1) === 1) {
                if (i && ((mask >> (i - 1)) & 1) === 0 && nums[i] === nums[i - 1]) {
                    ok = false;
                    break;
                }
                t.push(nums[i]);
            }
        }
        if (ok) {
            ans.push(t);
        }
    }
    return ans;
}
