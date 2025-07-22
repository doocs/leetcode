function checkPrimeFrequency(nums: number[]): boolean {
    const cnt: Record<number, number> = {};
    for (const x of nums) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    for (const x of Object.values(cnt)) {
        if (isPrime(x)) {
            return true;
        }
    }
    return false;
}

function isPrime(x: number): boolean {
    if (x < 2) {
        return false;
    }
    for (let i = 2; i * i <= x; i++) {
        if (x % i === 0) {
            return false;
        }
    }
    return true;
}
