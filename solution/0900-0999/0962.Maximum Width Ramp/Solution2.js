function maxWidthRamp(nums) {
    const idx = nums.map((x, i) => [x, i]).sort(([a], [b]) => a - b);
    let [ans, j] = [0, nums.length];

    for (const [_, i] of idx) {
        ans = Math.max(ans, i - j);
        j = Math.min(j, i);
    }

    return ans;
}
