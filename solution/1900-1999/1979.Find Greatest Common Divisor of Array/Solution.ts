function findGCD(nums: number[]): number {
    const min = Math.min(...nums);
    const max = Math.max(...nums);
    return gcd(min, max);
}

function gcd(a: number, b: number): number {
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}
