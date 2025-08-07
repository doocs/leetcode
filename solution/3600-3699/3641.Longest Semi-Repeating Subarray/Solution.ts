function longestSubarray(nums: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    let [ans, cur, l] = [0, 0, 0];
    for (let r = 0; r < nums.length; r++) {
        cnt.set(nums[r], (cnt.get(nums[r]) || 0) + 1);
        if (cnt.get(nums[r]) === 2) {
            cur++;
        }

        while (cur > k) {
            cnt.set(nums[l], cnt.get(nums[l])! - 1);
            if (cnt.get(nums[l]) === 1) {
                cur--;
            }
            l++;
        }

        ans = Math.max(ans, r - l + 1);
    }

    return ans;
}
