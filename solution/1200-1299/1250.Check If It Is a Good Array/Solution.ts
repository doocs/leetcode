function isGoodArray(nums: number[]): boolean {
    return nums.reduce(gcd) === 1;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
