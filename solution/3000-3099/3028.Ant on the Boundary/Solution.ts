function returnToBoundaryCount(nums: number[]): number {
    let [ans, s] = [0, 0];
    for (const x of nums) {
        s += x;
        ans += s === 0 ? 1 : 0;
    }
    return ans;
}
