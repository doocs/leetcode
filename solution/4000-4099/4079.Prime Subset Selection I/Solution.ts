const mx = 10 ** 6;

const primes: number[] = (() => {
    const isPrime = new Uint8Array(mx + 1).fill(1);
    isPrime[0] = isPrime[1] = 0;
    const primes: number[] = [];

    for (let i = 2; i <= mx; i++) {
        if (isPrime[i]) {
            primes.push(i);
            if (i <= mx / i) {
                for (let j = i * i; j <= mx; j += i) {
                    isPrime[j] = 0;
                }
            }
        }
    }
    return primes;
})();

function maxPrimes(n: number, s: number): number[] {
    const ans: number[] = [];
    for (const p of primes) {
        if (p > n || p > s) {
            break;
        }
        ans.push(p);
        s -= p;
    }
    return ans;
}
