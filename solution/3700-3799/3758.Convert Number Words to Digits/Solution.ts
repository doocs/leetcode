function convertNumber(s: string): string {
    const d: string[] = [
        'zero',
        'one',
        'two',
        'three',
        'four',
        'five',
        'six',
        'seven',
        'eight',
        'nine',
    ];
    const n = s.length;
    const ans: string[] = [];
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < d.length; ++j) {
            const t = d[j];
            const m = t.length;
            if (i + m <= n && s.substring(i, i + m) === t) {
                ans.push(j.toString());
                i += m - 1;
                break;
            }
        }
    }
    return ans.join('');
}
