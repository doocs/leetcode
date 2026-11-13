function countNonDecreasingSubarrays(nums: number[], k: number): number {
    const n = nums.length;
    const q: number[] = [];
    let head = 0;
    let ans = 0;
    let cost = 0;
    let r = n - 1;
    for (let l = n - 1; l >= 0; l--) {
        const x = nums[l];
        while (head < q.length && nums[q[q.length - 1]] <= x) {
            const s = q.pop()!;
            const end = head < q.length ? q[q.length - 1] : r + 1;
            cost += (x - nums[s]) * (end - s);
        }
        q.push(l);
        while (cost > k) {
            cost -= nums[q[head]] - nums[r];
            if (q[head] === r) {
                head++;
            }
            r--;
        }
        ans += r - l + 1;
    }
    return ans;
}
