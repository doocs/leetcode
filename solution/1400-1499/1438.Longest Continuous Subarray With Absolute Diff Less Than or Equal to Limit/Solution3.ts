function longestSubarray(nums: number[], limit: number): number {
    const maxq: number[] = [];
    const minq: number[] = [];
    const n = nums.length;
    let l = 0;
    for (let r = 0; r < n; ++r) {
        while (maxq.length && nums[maxq.at(-1)!] < nums[r]) {
            maxq.pop();
        }
        while (minq.length && nums[minq.at(-1)!] > nums[r]) {
            minq.pop();
        }
        maxq.push(r);
        minq.push(r);
        if (nums[maxq[0]] - nums[minq[0]] > limit) {
            l++;
            if (maxq[0] < l) {
                maxq.shift();
            }
            if (minq[0] < l) {
                minq.shift();
            }
        }
    }
    return n - l;
}
