function maximizeExpressionOfThree(nums: number[]): number {
    const inf = 1 << 30;
    let [a, b, c] = [-inf, -inf, inf];

    for (const x of nums) {
        if (x < c) {
            c = x;
        }
        if (x >= a) {
            b = a;
            a = x;
        } else if (x > b) {
            b = x;
        }
    }
    return a + b - c;
}
