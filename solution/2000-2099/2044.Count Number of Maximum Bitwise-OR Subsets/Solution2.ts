function countMaxOrSubsets(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    let mx = 0;

    for (let mask = 0; mask < 1 << n; mask++) {
        let t = 0;
        for (let i = 0; i < n; i++) {
            if ((mask >> i) & 1) {
                t |= nums[i];
            }
        }
        if (mx < t) {
            mx = t;
            ans = 1;
        } else if (mx === t) {
            ans++;
        }
    }

    return ans;
}
