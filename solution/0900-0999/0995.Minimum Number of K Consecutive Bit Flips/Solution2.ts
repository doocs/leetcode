function minKBitFlips(nums: number[], k: number): number {
    const n = nums.length;
    let [ans, flipped] = [0, 0];
    for (let i = 0; i < n; i++) {
        if (nums[i - k] === -1) {
            flipped ^= 1;
        }
        if (nums[i] === flipped) {
            if (i + k > n) {
                return -1;
            }
            flipped ^= 1;
            ++ans;
            nums[i] = -1;
        }
    }
    return ans;
}
