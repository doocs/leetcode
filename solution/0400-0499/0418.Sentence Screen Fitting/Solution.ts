function wordsTyping(sentence: string[], rows: number, cols: number): number {
    const s = sentence.join(' ') + ' ';
    let cur = 0;
    const m = s.length;
    for (let i = 0; i < rows; ++i) {
        cur += cols;
        if (s[cur % m] === ' ') {
            ++cur;
        } else {
            while (cur > 0 && s[(cur - 1) % m] !== ' ') {
                --cur;
            }
        }
    }
    return Math.floor(cur / m);
}
