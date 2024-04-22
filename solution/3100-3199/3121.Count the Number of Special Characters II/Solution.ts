function numberOfSpecialChars(word: string): number {
    const first: number[] = Array.from({ length: 'z'.charCodeAt(0) + 1 }, () => 0);
    const last: number[] = Array.from({ length: 'z'.charCodeAt(0) + 1 }, () => 0);
    for (let i = 0; i < word.length; ++i) {
        const j = word.charCodeAt(i);
        if (first[j] === 0) {
            first[j] = i + 1;
        }
        last[j] = i + 1;
    }
    let ans: number = 0;
    for (let i = 0; i < 26; ++i) {
        if (
            last['a'.charCodeAt(0) + i] &&
            first['A'.charCodeAt(0) + i] &&
            last['a'.charCodeAt(0) + i] < first['A'.charCodeAt(0) + i]
        ) {
            ++ans;
        }
    }
    return ans;
}
