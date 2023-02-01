function numberOfBeams(bank: string[]): number {
    let last = 0;
    let ans = 0;
    for (const r of bank) {
        let t = 0;
        for (const v of r) {
            if (v === '1') {
                t++;
            }
        }
        if (t !== 0) {
            ans += last * t;
            last = t;
        }
    }
    return ans;
}
