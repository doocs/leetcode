function countAlternatingSubarrays(nums: number[]): number {
    let [ans, s] = [1, 1];
    for (let i = 1; i < nums.length; ++i) {
        s = nums[i] !== nums[i - 1] ? s + 1 : 1;
        ans += s;
    }
    return ans;
}
