function minOperations(nums: number[]): number {
    let [ans, v] = [0, 0];
    for (let x of nums) {
        x ^= v;
        if (x === 0) {
            v ^= 1;
            ++ans;
        }
    }
    return ans;
}
