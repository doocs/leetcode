function freqAlphabets(s: string): string {
    const ans: string[] = [];
    for (let i = 0, n = s.length; i < n; ) {
        if (i + 2 < n && s[i + 2] === '#') {
            ans.push(String.fromCharCode(96 + +s.slice(i, i + 2)));
            i += 3;
        } else {
            ans.push(String.fromCharCode(96 + +s[i]));
            i++;
        }
    }
    return ans.join('');
}
