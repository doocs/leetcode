function minIncrementForUnique(nums: number[]): number {
    const n = Math.max(...nums) + 1 + nums.length;
    const cnt: number[] = Array.from({ length: n }, () => 0);
    let ans = 0;

    for (const x of nums) {
        cnt[x]++;
    }

    for (let i = 0; i < n; i++) {
        if (cnt[i] <= 1) continue;

        const diff = cnt[i] - 1;
        cnt[i + 1] += diff;
        ans += diff;
        cnt[i] = 1;
    }

    return ans;
}
