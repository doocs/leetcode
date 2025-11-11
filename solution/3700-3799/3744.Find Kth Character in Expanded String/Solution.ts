function kthCharacter(s: string, k: number): string {
    for (const w of s.split(' ')) {
        const m = ((1 + w.length) * w.length) / 2;
        if (k === m) {
            return ' ';
        }
        if (k > m) {
            k -= m + 1;
        } else {
            let cur = 0;
            for (let i = 0; ; ++i) {
                cur += i + 1;
                if (k < cur) {
                    return w[i];
                }
            }
        }
    }
    return ' ';
}
