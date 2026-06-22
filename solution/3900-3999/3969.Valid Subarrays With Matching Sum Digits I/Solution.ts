function countValidSubarrays(nums: number[], x: number): number {
    const n = nums.length;
    let ans = 0;

    for (let l = 0; l < n; l++) {
        let s = 0;

        for (let r = l; r < n; r++) {
            s += nums[r];

            if (s % 10 === x && Number(s.toString()[0]) === x) {
                ans++;
            }
        }
    }

    return ans;
}
