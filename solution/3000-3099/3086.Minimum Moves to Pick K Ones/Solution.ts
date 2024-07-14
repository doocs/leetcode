function minimumMoves(nums: number[], k: number, maxChanges: number): number {
    const n = nums.length;
    const cnt = Array(n + 1).fill(0);
    const s = Array(n + 1).fill(0);

    for (let i = 1; i <= n; i++) {
        cnt[i] = cnt[i - 1] + nums[i - 1];
        s[i] = s[i - 1] + i * nums[i - 1];
    }

    let ans = Infinity;
    for (let i = 1; i <= n; i++) {
        let t = 0;
        let need = k - nums[i - 1];

        for (let j of [i - 1, i + 1]) {
            if (need > 0 && 1 <= j && j <= n && nums[j - 1] === 1) {
                need--;
                t++;
            }
        }

        const c = Math.min(need, maxChanges);
        need -= c;
        t += c * 2;

        if (need <= 0) {
            ans = Math.min(ans, t);
            continue;
        }

        let l = 2,
            r = Math.max(i - 1, n - i);

        while (l <= r) {
            const mid = (l + r) >> 1;
            const [l1, r1] = [Math.max(1, i - mid), Math.max(0, i - 2)];
            const [l2, r2] = [Math.min(n + 1, i + 2), Math.min(n, i + mid)];

            const c1 = cnt[r1] - cnt[l1 - 1];
            const c2 = cnt[r2] - cnt[l2 - 1];

            if (c1 + c2 >= need) {
                const t1 = c1 * i - (s[r1] - s[l1 - 1]);
                const t2 = s[r2] - s[l2 - 1] - c2 * i;
                ans = Math.min(ans, t + t1 + t2);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
    }

    return ans;
}
