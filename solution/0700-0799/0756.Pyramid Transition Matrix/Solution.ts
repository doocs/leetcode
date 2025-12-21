function pyramidTransition(bottom: string, allowed: string[]): boolean {
    const d: number[][] = Array.from({ length: 7 }, () => Array(7).fill(0));
    for (const s of allowed) {
        const a = s.charCodeAt(0) - 65;
        const b = s.charCodeAt(1) - 65;
        const c = s.charCodeAt(2) - 65;
        d[a][b] |= 1 << c;
    }

    const f = new Map<string, boolean>();

    const dfs = (s: string, t: string[]): boolean => {
        if (s.length === 1) return true;
        if (t.length + 1 === s.length) {
            return dfs(t.join(''), []);
        }

        const key = s + '.' + t.join('');
        if (f.has(key)) return f.get(key)!;

        const i = t.length;
        const a = s.charCodeAt(i) - 65;
        const b = s.charCodeAt(i + 1) - 65;
        let cs = d[a][b];

        for (let c = 0; c < 7; c++) {
            if ((cs >> c) & 1) {
                t.push(String.fromCharCode(65 + c));
                if (dfs(s, t)) {
                    f.set(key, true);
                    return true;
                }
                t.pop();
            }
        }

        f.set(key, false);
        return false;
    };

    return dfs(bottom, []);
}
