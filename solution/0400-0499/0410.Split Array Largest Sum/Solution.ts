function splitArray(nums: number[], k: number): number {
    let left = 0;
    let right = 0;
    for (const x of nums) {
        left = Math.max(left, x);
        right += x;
    }
    const check = (mx: number) => {
        let s = 1 << 30;
        let cnt = 0;
        for (const x of nums) {
            s += x;
            if (s > mx) {
                s = x;
                ++cnt;
            }
        }
        return cnt <= k;
    };
    while (left < right) {
        const mid = (left + right) >> 1;
        if (check(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
