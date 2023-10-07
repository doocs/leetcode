function minTime(time: number[], m: number): number {
    let left = 0;
    let right = time.reduce((a, b) => a + b);
    const check = (t: number): boolean => {
        let s = 0;
        let mx = 0;
        let d = 1;
        for (const x of time) {
            s += x;
            mx = Math.max(mx, x);
            if (s - mx > t) {
                s = x;
                mx = x;
                d++;
            }
        }
        return d <= m;
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
