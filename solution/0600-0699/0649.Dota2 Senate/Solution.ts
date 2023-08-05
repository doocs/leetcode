function predictPartyVictory(senate: string): string {
    const n = senate.length;
    const qr: number[] = [];
    const qd: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (senate[i] === 'R') {
            qr.push(i);
        } else {
            qd.push(i);
        }
    }
    while (qr.length > 0 && qd.length > 0) {
        const r = qr.shift()!;
        const d = qd.shift()!;
        if (r < d) {
            qr.push(r + n);
        } else {
            qd.push(d + n);
        }
    }
    return qr.length > 0 ? 'Radiant' : 'Dire';
}
