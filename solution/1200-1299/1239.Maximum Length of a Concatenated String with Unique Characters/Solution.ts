function maxLength(arr: string[]): number {
    const s: number[] = [0];
    let ans = 0;
    for (const t of arr) {
        let x = 0;
        for (const c of t) {
            const b = c.charCodeAt(0) - 97;
            if ((x >> b) & 1) {
                x = 0;
                break;
            }
            x |= 1 << b;
        }

        if (x > 0) {
            for (let i = s.length - 1; ~i; --i) {
                const y = s[i];
                if ((x & y) === 0) {
                    s.push(x | y);
                    ans = Math.max(ans, bitCount(x | y));
                }
            }
        }
    }

    return ans;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
