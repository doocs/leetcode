function maxSubarray(nums: number[]): number {
    let mx = 0;
    for (const x of nums) {
        mx = Math.max(mx, x);
    }

    const cntS = new Array((mx << 1) | 1).fill(0);
    const cntD = new Array(mx + 1).fill(0);

    let ans = 0;
    let l = 0;

    for (let r = 0; r < nums.length; r++) {
        const x = nums[r];

        while (cntS[x] > 0 || cntD[x] > 0) {
            const y = nums[l++];
            for (let i = l; i < r; i++) {
                const z = nums[i];
                cntS[y + z]--;
                cntD[Math.abs(y - z)]--;
            }
        }

        for (let i = l; i < r; i++) {
            const y = nums[i];
            cntS[x + y]++;
            cntD[Math.abs(x - y)]++;
        }

        ans = Math.max(ans, r - l + 1);
    }

    return ans;
}
