function threeSum(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const res = [];
    const n = nums.length;
    for (let i = 0; i < n - 2; i++) {
        if (nums[i] > 0) {
            break;
        }
        const target = 0 - nums[i];
        let l = i + 1;
        let r = n - 1;
        while (l < r) {
            if (nums[l] + nums[r] === target) {
                res.push([nums[i], nums[l], nums[r]]);
                l++;
                r--;
                while (nums[l] === nums[l - 1]) {
                    l++;
                }
                while (nums[r] === nums[r + 1]) {
                    r--;
                }
            } else if (nums[l] + nums[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        while (nums[i] === nums[i + 1]) {
            i++;
        }
    }
    return res;
}
