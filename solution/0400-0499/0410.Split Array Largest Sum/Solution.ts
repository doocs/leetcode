function splitArray(nums: number[], k: number): number {
    let l = Math.max(...nums);
    let r = nums.reduce((a, b) => a + b);

    const check = (mx: number) => {
        let [s, cnt] = [0, 0];
        for (const x of nums) {
            s += x;
            if (s > mx) {
                s = x;
                if (++cnt === k) return false;
            }
        }
        return true;
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
