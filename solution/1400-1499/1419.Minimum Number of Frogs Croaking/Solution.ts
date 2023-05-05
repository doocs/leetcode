function minNumberOfFrogs(croakOfFrogs: string): number {
    const n = croakOfFrogs.length;
    if (n % 5 !== 0) {
        return -1;
    }
    const idx = (c: string): number => 'croak'.indexOf(c);
    const cnt: number[] = [0, 0, 0, 0, 0];
    let ans = 0;
    let x = 0;
    for (const c of croakOfFrogs) {
        const i = idx(c);
        ++cnt[i];
        if (i === 0) {
            ans = Math.max(ans, ++x);
        } else {
            if (--cnt[i - 1] < 0) {
                return -1;
            }
            if (i === 4) {
                --x;
            }
        }
    }
    return x > 0 ? -1 : ans;
}
