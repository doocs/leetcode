function smallestEquivalentString(s1: string, s2: string, baseStr: string): string {
    const p: number[] = Array.from({ length: 26 }, (_, i) => i);

    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };

    for (let i = 0; i < s1.length; i++) {
        const x = s1.charCodeAt(i) - 'a'.charCodeAt(0);
        const y = s2.charCodeAt(i) - 'a'.charCodeAt(0);
        const px = find(x);
        const py = find(y);
        if (px < py) {
            p[py] = px;
        } else {
            p[px] = py;
        }
    }

    const s: string[] = [];
    for (let i = 0; i < baseStr.length; i++) {
        const c = baseStr.charCodeAt(i) - 'a'.charCodeAt(0);
        s.push(String.fromCharCode('a'.charCodeAt(0) + find(c)));
    }
    return s.join('');
}
