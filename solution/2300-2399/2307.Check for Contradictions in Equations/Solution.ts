function checkContradictions(equations: string[][], values: number[]): boolean {
    const d: { [key: string]: number } = {};
    let n = 0;

    for (const e of equations) {
        for (const s of e) {
            if (!(s in d)) {
                d[s] = n;
                n++;
            }
        }
    }

    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const w: number[] = Array.from({ length: n }, () => 1.0);

    const find = (x: number): number => {
        if (p[x] !== x) {
            const root = find(p[x]);
            w[x] *= w[p[x]];
            p[x] = root;
        }
        return p[x];
    };

    for (let i = 0; i < equations.length; i++) {
        const a = d[equations[i][0]];
        const b = d[equations[i][1]];
        const v = values[i];

        const pa = find(a);
        const pb = find(b);

        if (pa !== pb) {
            p[pb] = pa;
            w[pb] = (v * w[a]) / w[b];
        } else if (Math.abs(v * w[a] - w[b]) >= 1e-5) {
            return true;
        }
    }

    return false;
}
