function validateCoupons(code: string[], businessLine: string[], isActive: boolean[]): string[] {
    const idx: number[] = [];
    const bs = new Set(['electronics', 'grocery', 'pharmacy', 'restaurant']);

    const check = (s: string): boolean => {
        if (s.length === 0) return false;
        for (let i = 0; i < s.length; i++) {
            const c = s[i];
            if (!/[a-zA-Z0-9_]/.test(c)) {
                return false;
            }
        }
        return true;
    };

    for (let i = 0; i < code.length; i++) {
        if (isActive[i] && bs.has(businessLine[i]) && check(code[i])) {
            idx.push(i);
        }
    }

    idx.sort((i, j) => {
        if (businessLine[i] !== businessLine[j]) {
            return businessLine[i] < businessLine[j] ? -1 : 1;
        }
        return code[i] < code[j] ? -1 : 1;
    });

    return idx.map(i => code[i]);
}
