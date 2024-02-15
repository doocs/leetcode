class Solution {
    countMatchingSubarrays(nums: number[], pattern: number[]): number {
        for (let i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) nums[i] = 1;
            else if (nums[i + 1] < nums[i]) nums[i] = -1;
            else nums[i] = 0;
        }
        nums[nums.length - 1] = 2;
        const n = nums.length;
        const m = pattern.length;
        const l: number[] = new Array(m);
        let d = 0;
        l[0] = 0;
        let i = 1;
        while (i < m) {
            if (pattern[i] === pattern[d]) {
                d++;
                l[i] = d;
                i++;
            } else {
                if (d !== 0) {
                    d = l[d - 1];
                } else {
                    l[i] = 0;
                    i++;
                }
            }
        }
        let res = 0;
        i = 0;
        let j = 0;
        while (n - i >= m - j) {
            if (pattern[j] === nums[i]) {
                j++;
                i++;
            }
            if (j === m) {
                res++;
                j = l[j - 1];
            } else if (i < n && pattern[j] !== nums[i]) {
                if (j !== 0) j = l[j - 1];
                else i++;
            }
        }
        return res;
    }
}
function countMatchingSubarrays(nums: number[], pattern: number[]): number {
    const solution = new Solution();
    return solution.countMatchingSubarrays(nums, pattern);
}
