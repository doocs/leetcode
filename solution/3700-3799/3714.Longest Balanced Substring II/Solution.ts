function longestBalanced(s: string): number {
    const x = calc1(s);
    const y = Math.max(calc2(s, 'a', 'b'), calc2(s, 'b', 'c'), calc2(s, 'a', 'c'));
    const z = calc3(s);
    return Math.max(x, y, z);
}

function calc1(s: string): number {
    let res = 0;
    const n = s.length;
    let i = 0;
    while (i < n) {
        let j = i + 1;
        while (j < n && s[j] === s[i]) j++;
        res = Math.max(res, j - i);
        i = j;
    }
    return res;
}

function calc2(s: string, a: string, b: string): number {
    let res = 0;
    const n = s.length;
    let i = 0;

    while (i < n) {
        while (i < n && s[i] !== a && s[i] !== b) i++;

        const pos = new Map<number, number>();
        pos.set(0, i - 1);

        let d = 0;
        while (i < n && (s[i] === a || s[i] === b)) {
            d += s[i] === a ? 1 : -1;

            const prev = pos.get(d);
            if (prev !== undefined) {
                res = Math.max(res, i - prev);
            } else {
                pos.set(d, i);
            }
            i++;
        }
    }
    return res;
}

function calc3(s: string): number {
    const pos = new Map<string, number>();
    pos.set(key(0, 0), -1);

    const cnt = [0, 0, 0];
    let res = 0;

    for (let i = 0; i < s.length; i++) {
        const c = s.charCodeAt(i) - 97;
        cnt[c]++;

        const x = cnt[0] - cnt[1];
        const y = cnt[1] - cnt[2];
        const k = key(x, y);

        const prev = pos.get(k);
        if (prev !== undefined) {
            res = Math.max(res, i - prev);
        } else {
            pos.set(k, i);
        }
    }
    return res;
}

function key(x: number, y: number): string {
    return x + '#' + y;
}
