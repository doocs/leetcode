function countConsistent(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        let x = nums[i];
        let y = x;
        for (let j = i; j < n; j++) {
            x &= nums[j];
            y = Math.min(y, nums[j]);
            if (x === y) {
                ans++;
            }
        }
    }
    return ans;
}
