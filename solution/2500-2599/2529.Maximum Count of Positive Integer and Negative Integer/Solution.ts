function maximumCount(nums: number[]): number {
    let [a, b] = [0, 0];
    for (const x of nums) {
        if (x > 0) {
            ++a;
        } else if (x < 0) {
            ++b;
        }
    }
    return Math.max(a, b);
}
