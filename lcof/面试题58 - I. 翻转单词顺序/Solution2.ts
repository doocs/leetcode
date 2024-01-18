function reverseWords(s: string): string {
    s = s.trim();
    const res = [];
    let l = s.length - 1;
    let r = s.length - 1;
    while (l >= 0) {
        while (s[l] !== ' ' && l >= 0) {
            l--;
        }
        res.push(s.substring(l + 1, r + 1));
        while (s[l] === ' ' && l >= 0) {
            l--;
        }
        r = l;
    }
    return res.join(' ');
}
