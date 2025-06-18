/**
 * @param {number[][]} nums
 * @return {number}
 */
var diagonalPrime = function (nums) {
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < n; i++) {
        if (isPrime(nums[i][i])) {
            ans = Math.max(ans, nums[i][i]);
        }
        if (isPrime(nums[i][n - i - 1])) {
            ans = Math.max(ans, nums[i][n - i - 1]);
        }
    }
    return ans;
};

function isPrime(x) {
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
