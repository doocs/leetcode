function minKBitFlips(nums: number[], k: number): number {
    const n = nums.length;
    let [ans, flipped] = [0, 0];

    for (let i = 0; i < n; i++) {
        if (nums[i - k] === -1) flipped--;
        if (nums[i] === (flipped & 1)) {
            flipped++;
            ans++;
            nums[i] = -1;

            if (n - k < i) return -1;
        }
    }

    return ans;
}
