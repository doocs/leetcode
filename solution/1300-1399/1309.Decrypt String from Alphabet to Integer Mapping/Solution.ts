function freqAlphabets(s: string): string {
    const n = s.length;
    const res = [];
    let i = 0;
    while (i < n) {
        let code: string;
        if (s[i + 2] === '#') {
            code = s.slice(i, i + 2);
            i += 3;
        } else {
            code = s[i];
            i += 1;
        }
        res.push(code);
    }
    return res.map(v => String.fromCharCode(96 + Number(v))).join('');
}
