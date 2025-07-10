const primes: number[] = [];
let x = 2;
const M = 1000;
while (primes.length < M) {
    let is_prime = true;
    for (const p of primes) {
        if (p * p > x) break;
        if (x % p === 0) {
            is_prime = false;
            break;
        }
    }
    if (is_prime) primes.push(x);
    x++;
}

function minNumberOfPrimes(n: number, m: number): number {
    const inf = 1e9;
    const f: number[] = Array(n + 1).fill(inf);
    f[0] = 0;

    for (const x of primes.slice(0, m)) {
        for (let i = x; i <= n; i++) {
            if (f[i - x] < inf) {
                f[i] = Math.min(f[i], f[i - x] + 1);
            }
        }
    }

    return f[n] < inf ? f[n] : -1;
}
