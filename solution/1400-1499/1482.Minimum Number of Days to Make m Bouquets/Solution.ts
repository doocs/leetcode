function minDays(bloomDay: number[], m: number, k: number): number {
    const mx = Math.max(...bloomDay);
    let [l, r] = [1, mx + 1];
    const check = (days: number): boolean => {
        let [cnt, cur] = [0, 0];
        for (const x of bloomDay) {
            cur = x <= days ? cur + 1 : 0;
            if (cur === k) {
                cnt++;
                cur = 0;
            }
        }
        return cnt >= m;
    };
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l > mx ? -1 : l;
}
