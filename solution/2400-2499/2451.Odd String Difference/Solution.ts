function oddString(words: string[]): string {
    const d: Map<string, string[]> = new Map();
    for (const s of words) {
        const cs: number[] = [];
        for (let i = 0; i < s.length - 1; ++i) {
            cs.push(s[i + 1].charCodeAt(0) - s[i].charCodeAt(0));
        }
        const t = cs.join(',');
        if (!d.has(t)) {
            d.set(t, []);
        }
        d.get(t)!.push(s);
    }
    for (const [_, ss] of d) {
        if (ss.length === 1) {
            return ss[0];
        }
    }
    return '';
}
