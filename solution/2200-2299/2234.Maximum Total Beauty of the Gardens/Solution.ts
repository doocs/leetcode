function maximumBeauty(
    flowers: number[],
    newFlowers: number,
    target: number,
    full: number,
    partial: number,
): number {
    flowers.sort((a, b) => a - b);
    const n = flowers.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        s[i] = s[i - 1] + flowers[i - 1];
    }
    let x = flowers.filter(f => f >= target).length;
    let ans = 0;
    for (; x <= n; ++x) {
        newFlowers -= x === 0 ? 0 : Math.max(target - flowers[n - x], 0);
        if (newFlowers < 0) {
            break;
        }
        let l = 0;
        let r = n - x - 1;
        while (l < r) {
            const mid = (l + r + 1) >> 1;
            if (flowers[mid] * (mid + 1) - s[mid + 1] <= newFlowers) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        let y = 0;
        if (r !== -1) {
            const cost = flowers[l] * (l + 1) - s[l + 1];
            y = Math.min(flowers[l] + Math.floor((newFlowers - cost) / (l + 1)), target - 1);
        }
        ans = Math.max(ans, x * full + y * partial);
    }
    return ans;
}
