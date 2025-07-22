function maximumBobPoints(numArrows: number, aliceArrows: number[]): number[] {
    let [st, mx] = [0, 0];
    const m = aliceArrows.length;
    for (let mask = 1; mask < 1 << m; mask++) {
        let [cnt, s] = [0, 0];
        for (let i = 0; i < m; i++) {
            if ((mask >> i) & 1) {
                cnt += aliceArrows[i] + 1;
                s += i;
            }
        }
        if (cnt <= numArrows && s > mx) {
            mx = s;
            st = mask;
        }
    }
    const ans: number[] = Array(m).fill(0);
    for (let i = 0; i < m; i++) {
        if ((st >> i) & 1) {
            ans[i] = aliceArrows[i] + 1;
            numArrows -= ans[i];
        }
    }
    ans[0] += numArrows;
    return ans;
}
