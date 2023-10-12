function minArrayLength(nums: number[], k: number): number {
    let [ans, y] = [1, nums[0]];
    for (const x of nums.slice(1)) {
        if (x === 0) {
            return 1;
        }
        if (x * y <= k) {
            y *= x;
        } else {
            y = x;
            ++ans;
        }
    }
    return ans;
}
