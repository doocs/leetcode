function wordPattern(pattern: string, s: string): boolean {
    const ws = s.split(' ');
    if (pattern.length !== ws.length) {
        return false;
    }
    const d1 = new Map<string, string>();
    const d2 = new Map<string, string>();
    for (let i = 0; i < pattern.length; ++i) {
        const a = pattern[i];
        const b = ws[i];
        if (d1.has(a) && d1.get(a) !== b) {
            return false;
        }
        if (d2.has(b) && d2.get(b) !== a) {
            return false;
        }
        d1.set(a, b);
        d2.set(b, a);
    }
    return true;
}
