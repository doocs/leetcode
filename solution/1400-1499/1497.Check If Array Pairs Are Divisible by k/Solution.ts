function canArrange(arr: number[], k: number): boolean {
    const cnt: number[] = Array(k).fill(0);
    for (const x of arr) {
        ++cnt[((x % k) + k) % k];
    }
    for (let i = 1; i < k; ++i) {
        if (cnt[i] !== cnt[k - i]) {
            return false;
        }
    }
    return cnt[0] % 2 === 0;
}
