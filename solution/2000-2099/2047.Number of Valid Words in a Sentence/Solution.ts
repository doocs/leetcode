function countValidWords(sentence: string): number {
    const check = (s: string): number => {
        if (s.length === 0) {
            return 0;
        }
        let st = false;
        for (let i = 0; i < s.length; ++i) {
            if (/\d/.test(s[i])) {
                return 0;
            }
            if (['!', '.', ','].includes(s[i]) && i < s.length - 1) {
                return 0;
            }
            if (s[i] === '-') {
                if (st || [0, s.length - 1].includes(i)) {
                    return 0;
                }
                if (!/[a-zA-Z]/.test(s[i - 1]) || !/[a-zA-Z]/.test(s[i + 1])) {
                    return 0;
                }
                st = true;
            }
        }
        return 1;
    };
    return sentence.split(/\s+/).reduce((acc, s) => acc + check(s), 0);
}
