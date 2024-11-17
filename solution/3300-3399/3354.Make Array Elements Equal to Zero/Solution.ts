function countValidSelections(nums: number[]): number {
    const s = nums.reduce((acc, x) => acc + x, 0);
    let [ans, l] = [0, 0];
    for (const x of nums) {
        if (x) {
            l += x;
        } else if (l * 2 === s) {
            ans += 2;
        } else if (Math.abs(l * 2 - s) <= 1) {
            ++ans;
        }
    }
    return ans;
}
