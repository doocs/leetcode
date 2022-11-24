function freqAlphabets(s: string): string {
    const n = s.length;
    const ans = [];
    let i = 0;
    while (i < n) {
        if (s[i + 2] == '#') {
            ans.push(s.slice(i, i + 2));
            i += 3;
        } else {
            ans.push(s[i]);
            i += 1;
        }
    }
    return ans
        .map(c => String.fromCharCode('a'.charCodeAt(0) + Number(c) - 1))
        .join('');
}
