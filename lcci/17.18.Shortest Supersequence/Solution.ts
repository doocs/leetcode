function shortestSeq(big: number[], small: number[]): number[] {
    let cnt = small.length;
    const need: Map<number, number> = new Map();
    const window: Map<number, number> = new Map();
    for (const x of small) {
        need.set(x, 1);
    }
    let k = -1;
    let mi = 1 << 30;
    for (let i = 0, j = 0; i < big.length; ++i) {
        window.set(big[i], (window.get(big[i]) ?? 0) + 1);
        if ((need.get(big[i]) ?? 0) >= window.get(big[i])!) {
            --cnt;
        }
        while (cnt === 0) {
            if (i - j + 1 < mi) {
                mi = i - j + 1;
                k = j;
            }
            if ((need.get(big[j]) ?? 0) >= window.get(big[j])!) {
                ++cnt;
            }
            window.set(big[j], window.get(big[j])! - 1);
            ++j;
        }
    }
    return k < 0 ? [] : [k, k + mi - 1];
}
