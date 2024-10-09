function minIncrementForUnique(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let [ans, y] = [0, -1];
    for (const x of nums) {
        y = Math.max(y + 1, x);
        ans += y - x;
    }
    return ans;
}
