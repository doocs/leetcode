function findLongestWord(s: string, dictionary: string[]): string {
    dictionary.sort((a, b) => {
        if (a.length === b.length) {
            return b < a ? 1 : -1;
        }
        return b.length - a.length;
    });
    const n = s.length;
    for (const target of dictionary) {
        const m = target.length;
        if (m > n) {
            continue;
        }
        let i = 0;
        let j = 0;
        while (i < n && j < m) {
            if (s[i] === target[j]) {
                j++;
            }
            i++;
        }
        if (j === m) {
            return target;
        }
    }
    return '';
}
