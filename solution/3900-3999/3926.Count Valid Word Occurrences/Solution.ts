function countWordOccurrences(chunks: string[], queries: string[]): number[] {
    const s = chunks.join('');
    const n = s.length;
    const cnt = new Map<string, number>();
    let i = 0;
    while (i < n) {
        if (s[i] === ' ' || s[i] === '-') {
            i++;
            continue;
        }
        let j = i;
        while (
            j < n &&
            s[j] !== ' ' &&
            (s[j] !== '-' || (j + 1 < n && s[j + 1] !== ' ' && s[j + 1] !== '-'))
        ) {
            j++;
        }
        const word = s.substring(i, j);
        cnt.set(word, (cnt.get(word) || 0) + 1);
        i = j;
    }
    return queries.map(q => cnt.get(q) || 0);
}
