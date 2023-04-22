function isIsomorphic(s: string, t: string): boolean {
    const d1: number[] = new Array(256).fill(0);
    const d2: number[] = new Array(256).fill(0);
    for (let i = 0; i < s.length; ++i) {
        const a = s.charCodeAt(i);
        const b = t.charCodeAt(i);
        if (d1[a] !== d2[b]) {
            return false;
        }
        d1[a] = i + 1;
        d2[b] = i + 1;
    }
    return true;
}
