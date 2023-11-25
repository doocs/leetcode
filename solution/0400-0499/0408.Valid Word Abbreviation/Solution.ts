function validWordAbbreviation(word: string, abbr: string): boolean {
    const [m, n] = [word.length, abbr.length];
    let [i, j, x] = [0, 0, 0];
    for (; i < m && j < n; ++j) {
        if (abbr[j] >= '0' && abbr[j] <= '9') {
            if (abbr[j] === '0' && x === 0) {
                return false;
            }
            x = x * 10 + Number(abbr[j]);
        } else {
            i += x;
            x = 0;
            if (i >= m || word[i++] !== abbr[j]) {
                return false;
            }
        }
    }
    return i + x === m && j === n;
}
