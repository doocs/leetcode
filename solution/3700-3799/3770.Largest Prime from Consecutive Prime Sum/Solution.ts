const MX = 500000;

const isPrime: boolean[] = Array(MX + 1).fill(true);
isPrime[0] = false;
isPrime[1] = false;

const primes: number[] = [];
const s: number[] = [];

(function init() {
    for (let i = 2; i <= MX; i++) {
        if (isPrime[i]) {
            primes.push(i);
            if (i * i <= MX) {
                for (let j = i * i; j <= MX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    s.push(0);
    let t = 0;
    for (const x of primes) {
        t += x;
        if (t > MX) break;
        if (isPrime[t]) {
            s.push(t);
        }
    }
})();

function largestPrime(n: number): number {
    const i = _.sortedIndex(s, n + 1) - 1;
    return s[i];
}
