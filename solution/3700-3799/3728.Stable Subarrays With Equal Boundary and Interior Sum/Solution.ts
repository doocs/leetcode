function countStableSubarrays(capacity: number[]): number {
    const n = capacity.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        s[i] = s[i - 1] + capacity[i - 1];
    }

    const cnt = new Map<string, number>();
    let ans = 0;

    for (let r = 2; r < n; r++) {
        const l = r - 2;
        const keyL = `${capacity[l]},${capacity[l] + s[l + 1]}`;
        cnt.set(keyL, (cnt.get(keyL) || 0) + 1);

        const keyR = `${capacity[r]},${s[r]}`;
        ans += cnt.get(keyR) || 0;
    }

    return ans;
}
