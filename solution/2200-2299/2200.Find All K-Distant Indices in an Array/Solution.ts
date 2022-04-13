function findKDistantIndices(nums: number[], key: number, k: number): number[] {
    const n = nums.length;
    let ans = [];
    for (let j = 0; j < n; j++) {
        if (nums[j] == key) {
            for (let i = j - k; i <= j + k; i++) {
                if (i >= 0 && i < n && !ans.includes(i)) {
                    ans.push(i);
                }
            }
        }
    }
    return ans;
}
