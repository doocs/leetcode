function maxPower(stations: number[], r: number, k: number): number {
    function check(x: bigint, k: bigint): boolean {
        d.fill(0n);
        let t = 0n;
        for (let i = 0; i < n; ++i) {
            t += d[i];
            const dist = x - (s[i] + t);
            if (dist > 0) {
                if (k < dist) {
                    return false;
                }
                k -= dist;
                const j = Math.min(i + r, n - 1);
                const left = Math.max(0, j - r);
                const right = Math.min(j + r, n - 1);
                d[left] += dist;
                d[right + 1] -= dist;
                t += dist;
            }
        }
        return true;
    }
    const n = stations.length;
    const d: bigint[] = new Array(n + 1).fill(0n);
    const s: bigint[] = new Array(n + 1).fill(0n);

    for (let i = 0; i < n; ++i) {
        const left = Math.max(0, i - r);
        const right = Math.min(i + r, n - 1);
        d[left] += BigInt(stations[i]);
        d[right + 1] -= BigInt(stations[i]);
    }

    s[0] = d[0];
    for (let i = 1; i < n + 1; ++i) {
        s[i] = s[i - 1] + d[i];
    }

    let left = 0n,
        right = 1n << 40n;
    while (left < right) {
        const mid = (left + right + 1n) >> 1n;
        if (check(mid, BigInt(k))) {
            left = mid;
        } else {
            right = mid - 1n;
        }
    }
    return Number(left);
}
