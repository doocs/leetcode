function findPrimePairs(n: number): number[][] {
    const primes: boolean[] = new Array(n).fill(true);
    for (let i = 2; i < n; ++i) {
        if (primes[i]) {
            for (let j = i + i; j < n; j += i) {
                primes[j] = false;
            }
        }
    }
    const ans: number[][] = [];
    for (let x = 2; x <= n / 2; ++x) {
        const y = n - x;
        if (primes[x] && primes[y]) {
            ans.push([x, y]);
        }
    }
    return ans;
}
