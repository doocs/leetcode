const M = 100000 + 10;
const primes: boolean[] = Array(M).fill(true);

const init = (() => {
    primes[0] = primes[1] = false;

    for (let i = 2; i < M; i++) {
        if (primes[i]) {
            for (let j = i + i; j < M; j += i) {
                primes[j] = false;
            }
        }
    }
})();

function splitArray(nums: number[]): number {
    let ans = 0;
    for (let i = 0; i < nums.length; i++) {
        ans += primes[i] ? nums[i] : -nums[i];
    }
    return Math.abs(ans);
}
