function getSmallestString(s: string): string {
    const n = s.length;
    const cs: string[] = s.split('');
    for (let i = 1; i < n; ++i) {
        const a = cs[i - 1];
        const b = cs[i];
        if (a > b && +a % 2 === +b % 2) {
            cs[i - 1] = b;
            cs[i] = a;
            return cs.join('');
        }
    }
    return s;
}
