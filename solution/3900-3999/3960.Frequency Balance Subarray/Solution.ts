function getLength(nums: number[]): number {
    const n = nums.length;
    let ans = 1;

    for (let l = 0; l < n; l++) {
        const cnt = new Map<number, number>();
        const freq = new Map<number, number>();

        for (let r = l; r < n; r++) {
            const x = nums[r];
            const c = cnt.get(x) ?? 0;

            if ((freq.get(c) ?? 0) > 0) {
                const f = (freq.get(c) ?? 0) - 1;
                if (f === 0) {
                    freq.delete(c);
                } else {
                    freq.set(c, f);
                }
            }

            cnt.set(x, c + 1);
            freq.set(c + 1, (freq.get(c + 1) ?? 0) + 1);

            const cur = c + 1;

            if (
                cnt.size === 1 ||
                (freq.size === 2 &&
                    ((freq.get(cur * 2) ?? 0) > 0 ||
                        (cur % 2 === 0 && (freq.get(cur / 2) ?? 0) > 0)))
            ) {
                ans = Math.max(ans, r - l + 1);
            }
        }
    }

    return ans;
}
