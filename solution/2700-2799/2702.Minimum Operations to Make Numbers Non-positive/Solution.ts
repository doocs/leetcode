function minOperations(nums: number[], x: number, y: number): number {
    let l = 0;
    let r = Math.max(...nums);
    const check = (t: number): boolean => {
        let cnt = 0;
        for (const v of nums) {
            if (v > t * y) {
                cnt += Math.ceil((v - t * y) / (x - y));
            }
        }
        return cnt <= t;
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
