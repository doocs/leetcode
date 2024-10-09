function maximumValueSum(nums: number[], k: number, edges: number[][]): number {
    let [f0, f1] = [0, -Infinity];
    for (const x of nums) {
        [f0, f1] = [Math.max(f0 + x, f1 + (x ^ k)), Math.max(f1 + x, f0 + (x ^ k))];
    }
    return f0;
}
