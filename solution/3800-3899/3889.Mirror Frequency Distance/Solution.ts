function mirrorFrequency(s: string): number {
    const freq = new Map<string, number>();
    for (const c of s) {
        freq.set(c, (freq.get(c) || 0) + 1);
    }

    let ans = 0;
    const vis = new Set<string>();

    for (const [c, v] of freq.entries()) {
        let m: string;

        if (/[a-z]/.test(c)) {
            m = String.fromCharCode('a'.charCodeAt(0) + 25 - (c.charCodeAt(0) - 'a'.charCodeAt(0)));
        } else {
            m = String(9 - Number(c));
        }

        if (vis.has(m)) {
            continue;
        }
        vis.add(c);

        const mv = freq.get(m) || 0;
        ans += Math.abs(v - mv);
    }

    return ans;
}
