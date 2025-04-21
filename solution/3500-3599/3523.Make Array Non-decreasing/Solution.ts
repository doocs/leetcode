function maximumPossibleSize(nums: number[]): number {
    let [ans, mx] = [0, 0];
    for (const x of nums) {
        if (mx <= x) {
            ++ans;
            mx = x;
        }
    }
    return ans;
}
