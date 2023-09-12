function sampleStats(count: number[]): number[] {
    const find = (i: number): number => {
        for (let k = 0, t = 0; ; ++k) {
            t += count[k];
            if (t >= i) {
                return k;
            }
        }
    };
    let mi = 1 << 30;
    let mx = -1;
    let [s, cnt, mode] = [0, 0, 0];
    for (let k = 0; k < count.length; ++k) {
        if (count[k] > 0) {
            mi = Math.min(mi, k);
            mx = Math.max(mx, k);
            s += k * count[k];
            cnt += count[k];
            if (count[k] > count[mode]) {
                mode = k;
            }
        }
    }
    const median =
        cnt % 2 === 1 ? find((cnt >> 1) + 1) : (find(cnt >> 1) + find((cnt >> 1) + 1)) / 2;
    return [mi, mx, s / cnt, median, mode];
}
