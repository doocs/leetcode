function sumIndicesWithKSetBits(nums: number[], k: number): number {
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (bitCount(i) === k) {
            ans += nums[i];
        }
    }
    return ans;
}

function bitCount(n: number): number {
    let count = 0;
    while (n) {
        n &= n - 1;
        count++;
    }
    return count;
}
