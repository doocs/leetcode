function maximizeSweetness(sweetness: number[], k: number): number {
    let l = 0;
    let r = sweetness.reduce((a, b) => a + b);
    const check = (x: number): boolean => {
        let s = 0;
        let cnt = 0;
        for (const v of sweetness) {
            s += v;
            if (s >= x) {
                s = 0;
                ++cnt;
            }
        }
        return cnt > k;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
