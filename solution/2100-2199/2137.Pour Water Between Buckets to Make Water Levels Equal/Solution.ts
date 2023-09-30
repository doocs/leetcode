function equalizeWater(buckets: number[], loss: number): number {
    let l = 0;
    let r = Math.max(...buckets);
    const check = (v: number): boolean => {
        let [a, b] = [0, 0];
        for (const x of buckets) {
            if (x >= v) {
                a += x - v;
            } else {
                b += ((v - x) * 100) / (100 - loss);
            }
        }
        return a >= b;
    };
    while (r - l > 1e-5) {
        const mid = (l + r) / 2;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid;
        }
    }
    return l;
}
