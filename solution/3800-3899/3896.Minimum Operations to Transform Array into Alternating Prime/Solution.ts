const MX = 200000;

const isPrime: boolean[] = (() => {
    const p = Array<boolean>(MX + 1).fill(true);
    p[0] = p[1] = false;
    for (let i = 2; i <= Math.floor(MX / i); ++i) {
        if (p[i]) {
            for (let j = i * i; j <= MX; j += i) {
                p[j] = false;
            }
        }
    }
    return p;
})();

const primes: number[] = Array.from({ length: MX - 1 }, (_, i) => i + 2).filter(i => isPrime[i]);

function minOperations(nums: number[]): number {
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        const x = nums[i];
        if ((i & 1) === 0) {
            const j = _.sortedIndex(primes, x);
            ans += primes[j] - x;
        } else if (isPrime[x]) {
            ans += x === 2 ? 2 : 1;
        }
    }
    return ans;
}
