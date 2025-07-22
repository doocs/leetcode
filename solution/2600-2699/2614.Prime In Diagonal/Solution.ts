function diagonalPrime(nums: number[][]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (isPrime(nums[i][i])) {
            ans = Math.max(ans, nums[i][i]);
        }
        if (isPrime(nums[i][n - i - 1])) {
            ans = Math.max(ans, nums[i][n - i - 1]);
        }
    }
    return ans;
}

function isPrime(x: number): boolean {
    if (x < 2) {
        return false;
    }
    for (let i = 2; i <= Math.floor(x / i); ++i) {
        if (x % i === 0) {
            return false;
        }
    }
    return true;
}
