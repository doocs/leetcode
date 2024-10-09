const m = 31623;
const primes: boolean[] = Array(m + 1).fill(true);

(() => {
    primes[0] = primes[1] = false;
    for (let i = 2; i <= m; ++i) {
        if (primes[i]) {
            for (let j = i * 2; j <= m; j += i) {
                primes[j] = false;
            }
        }
    }
})();

function nonSpecialCount(l: number, r: number): number {
    const lo = Math.ceil(Math.sqrt(l));
    const hi = Math.floor(Math.sqrt(r));
    let cnt = 0;
    for (let i = lo; i <= hi; ++i) {
        if (primes[i]) {
            ++cnt;
        }
    }
    return r - l + 1 - cnt;
}
