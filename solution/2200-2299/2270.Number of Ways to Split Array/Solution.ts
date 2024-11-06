function waysToSplitArray(nums: number[]): number {
    const s = nums.reduce((acc, cur) => acc + cur, 0);
    let [ans, t] = [0, 0];
    for (const x of nums.slice(0, -1)) {
        t += x;
        if (t >= s - t) {
            ++ans;
        }
    }
    return ans;
}
