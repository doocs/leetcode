function isPerfectSquare(num: number): boolean {
    let [l, r] = [1, num];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (mid >= num / mid) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l * l === num;
}
