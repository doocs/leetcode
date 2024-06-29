function findKthLargest(nums: number[], k: number): number {
    const n = 10 ** 4;
    const length = n * 2 + 1;
    const cnt = Array(length);

    for (const x of nums) {
        cnt[x + n] = (cnt[x + n] ?? 0) + 1;
    }

    for (let i = length; i >= 0; i--) {
        if (!cnt[i]) continue;
        k -= cnt[i];
        if (k <= 0) return i - n;
    }

    return -1;
}
