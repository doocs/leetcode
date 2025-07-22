function digitSum(s: string, k: number): string {
    while (s.length > k) {
        const t: number[] = [];
        for (let i = 0; i < s.length; i += k) {
            const x = s
                .slice(i, i + k)
                .split('')
                .reduce((a, b) => a + +b, 0);
            t.push(x);
        }
        s = t.join('');
    }
    return s;
}
