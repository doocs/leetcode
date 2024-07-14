function distinctPrimeFactors(nums: number[]): number {
    const s: Set<number> = new Set();
    for (let n of nums) {
        let i = 2;
        while (i <= n / i) {
            if (n % i === 0) {
                s.add(i);
                while (n % i === 0) {
                    n = Math.floor(n / i);
                }
            }
            ++i;
        }
        if (n > 1) {
            s.add(n);
        }
    }
    return s.size;
}
