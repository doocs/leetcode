function minimumK(nums: number[]): number {
    const check = (k: number): boolean => {
        let t = 0;
        for (const x of nums) {
            t += Math.floor((x + k - 1) / k);
        }
        return t <= k * k;
    };

    let l = 1,
        r = 100000;
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
