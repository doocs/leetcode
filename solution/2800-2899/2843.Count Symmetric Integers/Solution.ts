function countSymmetricIntegers(low: number, high: number): number {
    let ans = 0;
    const f = (x: number): number => {
        const s = x.toString();
        const n = s.length;
        if (n & 1) {
            return 0;
        }
        let a = 0;
        let b = 0;
        for (let i = 0; i < n >> 1; ++i) {
            a += Number(s[i]);
            b += Number(s[(n >> 1) + i]);
        }
        return a === b ? 1 : 0;
    };
    for (let x = low; x <= high; ++x) {
        ans += f(x);
    }
    return ans;
}
