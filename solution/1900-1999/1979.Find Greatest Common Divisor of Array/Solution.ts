function findGCD(nums: number[]): number {
    let a = 1;
    let b = 1000;
    for (const x of nums) {
        a = Math.max(a, x);
        b = Math.min(b, x);
    }
    return gcd(a, b);
}

function gcd(a: number, b: number): number {
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}
