function sumOfUnique(nums: number[]): number {
    const cnt = new Array(101).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    let ans = 0;
    for (let x = 0; x < 101; ++x) {
        if (cnt[x] === 1) {
            ans += x;
        }
    }
    return ans;
}
