function findClosestNumber(nums: number[]): number {
    let [ans, d] = [0, 1 << 30];
    for (const x of nums) {
        const y = Math.abs(x);
        if (y < d || (y == d && x > ans)) {
            [ans, d] = [x, y];
        }
    }
    return ans;
}
