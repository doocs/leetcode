function medianOfUniquenessArray(nums: number[]): number {
    const n = nums.length;
    const m = Math.floor(((1 + n) * n) / 2);
    let [l, r] = [0, n];
    const check = (mx: number): boolean => {
        const cnt = new Map<number, number>();
        let [l, k] = [0, 0];
        for (let r = 0; r < n; ++r) {
            const x = nums[r];
            cnt.set(x, (cnt.get(x) || 0) + 1);
            while (cnt.size > mx) {
                const y = nums[l++];
                cnt.set(y, cnt.get(y)! - 1);
                if (cnt.get(y) === 0) {
                    cnt.delete(y);
                }
            }
            k += r - l + 1;
            if (k >= Math.floor((m + 1) / 2)) {
                return true;
            }
        }
        return false;
    };
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
