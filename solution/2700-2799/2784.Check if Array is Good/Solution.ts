function isGood(nums: number[]): boolean {
    const n = nums.length - 1;
    const cnt: number[] = new Array(201).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    cnt[n] -= 2;
    for (let i = 1; i < n; ++i) {
        cnt[i]--;
    }
    return cnt.every(x => x >= 0);
}
