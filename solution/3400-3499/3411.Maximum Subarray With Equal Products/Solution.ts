function maxLength(nums: number[]): number {
    const gcd = (a: number, b: number): number => (b ? gcd(b, a % b) : a);
    let best = 2,
        left = 0,
        product = 1;
    for (const [right, x] of nums.entries()) {
        while (gcd(product, x) > 1) product /= nums[left++];
        product *= x;
        best = Math.max(best, right - left + 1);
    }
    return best;
}
