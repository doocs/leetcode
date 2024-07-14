function getSmallestString(s: string, k: number): string {
    const cs: string[] = s.split('');
    for (let i = 0; i < s.length; ++i) {
        for (let j = 97; j < s[i].charCodeAt(0); ++j) {
            const d = Math.min(s[i].charCodeAt(0) - j, 26 - s[i].charCodeAt(0) + j);
            if (d <= k) {
                cs[i] = String.fromCharCode(j);
                k -= d;
                break;
            }
        }
    }
    return cs.join('');
}
