function maxPartitionsAfterOperations(s: string, k: number): number {
    const n = s.length;
    const masks = new Array(n);
    for (let i = 0; i < n; ++i) {
        masks[i] = 1 << (s.charCodeAt(i) - 97);
    }
    const reach: Set<number>[] = Array.from({ length: n + 1 }, () => new Set());
    const f: Map<number, number>[] = Array.from({ length: n + 1 }, () => new Map());
    reach[0].add(1);
    for (let i = 0; i < n; ++i) {
        const v = masks[i];
        for (const key of reach[i]) {
            const cur = key >> 1;
            const t = key & 1;
            let nxt = cur | v;
            if (bitCount(nxt) > k) {
                reach[i + 1].add((v << 1) | t);
            } else {
                reach[i + 1].add((nxt << 1) | t);
            }
            if (t) {
                for (let j = 0; j < 26; ++j) {
                    const bit = 1 << j;
                    nxt = cur | bit;
                    if (bitCount(nxt) > k) {
                        reach[i + 1].add(bit << 1);
                    } else {
                        reach[i + 1].add(nxt << 1);
                    }
                }
            }
        }
    }
    const get = (i: number, key: number): number => {
        if (i === n) {
            return 1;
        }
        return f[i].get(key)!;
    };
    for (let i = n - 1; i >= 0; --i) {
        const v = masks[i];
        for (const key of reach[i]) {
            const cur = key >> 1;
            const t = key & 1;
            let nxt = cur | v;
            let ans = 0;
            if (bitCount(nxt) > k) {
                ans = get(i + 1, (v << 1) | t) + 1;
            } else {
                ans = get(i + 1, (nxt << 1) | t);
            }
            if (t) {
                for (let j = 0; j < 26; ++j) {
                    const bit = 1 << j;
                    nxt = cur | bit;
                    if (bitCount(nxt) > k) {
                        ans = Math.max(ans, get(i + 1, bit << 1) + 1);
                    } else {
                        ans = Math.max(ans, get(i + 1, nxt << 1));
                    }
                }
            }
            f[i].set(key, ans);
        }
    }
    return f[0].get(1)!;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
