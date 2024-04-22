function friendRequests(n: number, restrictions: number[][], requests: number[][]): boolean[] {
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    const ans: boolean[] = [];
    for (const [u, v] of requests) {
        const pu = find(u);
        const pv = find(v);
        if (pu === pv) {
            ans.push(true);
        } else {
            let ok = true;
            for (const [x, y] of restrictions) {
                const px = find(x);
                const py = find(y);
                if ((px === pu && py === pv) || (px === pv && py === pu)) {
                    ok = false;
                    break;
                }
            }
            ans.push(ok);
            if (ok) {
                p[pu] = pv;
            }
        }
    }
    return ans;
}
