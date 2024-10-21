const mx = 10 ** 6;
const lpf = Array(mx + 1).fill(0);
for (let i = 2; i <= mx; ++i) {
    for (let j = i; j <= mx; j += i) {
        if (lpf[j] === 0) {
            lpf[j] = i;
        }
    }
}

function minOperations(nums: number[]): number {
    let ans = 0;
    for (let i = nums.length - 2; ~i; --i) {
        if (nums[i] > nums[i + 1]) {
            nums[i] = lpf[nums[i]];
            if (nums[i] > nums[i + 1]) {
                return -1;
            }
            ++ans;
        }
    }
    return ans;
}
