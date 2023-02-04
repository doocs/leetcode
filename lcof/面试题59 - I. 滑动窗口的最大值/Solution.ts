function maxSlidingWindow(nums: number[], k: number): number[] {
    const q: number[] = [];
    const n = nums.length;
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        while (q.length && i - q[0] + 1 > k) {
            q.shift();
        }
        while (q.length && nums[q[q.length - 1]] <= nums[i]) {
            q.pop();
        }
        q.push(i);
        if (i >= k - 1) {
            ans.push(nums[q[0]]);
        }
    }
    return ans;
}
