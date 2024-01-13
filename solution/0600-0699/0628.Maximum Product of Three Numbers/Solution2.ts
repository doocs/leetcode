function maximumProduct(nums: number[]): number {
    const inf = 1 << 30;
    let mi1 = inf,
        mi2 = inf;
    let mx1 = -inf,
        mx2 = -inf,
        mx3 = -inf;
    for (const x of nums) {
        if (x < mi1) {
            mi2 = mi1;
            mi1 = x;
        } else if (x < mi2) {
            mi2 = x;
        }
        if (x > mx1) {
            mx3 = mx2;
            mx2 = mx1;
            mx1 = x;
        } else if (x > mx2) {
            mx3 = mx2;
            mx2 = x;
        } else if (x > mx3) {
            mx3 = x;
        }
    }
    return Math.max(mi1 * mi2 * mx1, mx1 * mx2 * mx3);
}
