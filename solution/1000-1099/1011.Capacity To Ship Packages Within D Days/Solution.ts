function shipWithinDays(weights: number[], days: number): number {
    let left = 0;
    let right = 0;
    for (const w of weights) {
        left = Math.max(left, w);
        right += w;
    }
    const check = (mx: number) => {
        let ws = 0;
        let cnt = 1;
        for (const w of weights) {
            ws += w;
            if (ws > mx) {
                ws = w;
                ++cnt;
            }
        }
        return cnt <= days;
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
