function smallestSubsequence(s: string): string {
    const f = (c: string): number => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const last: number[] = new Array(26).fill(0);
    for (const [i, c] of [...s].entries()) {
        last[f(c)] = i;
    }
    const stk: string[] = [];
    let mask = 0;
    for (const [i, c] of [...s].entries()) {
        const x = f(c);
        if ((mask >> x) & 1) {
            continue;
        }
        while (stk.length && stk[stk.length - 1] > c && last[f(stk[stk.length - 1])] > i) {
            mask ^= 1 << f(stk.pop()!);
        }
        stk.push(c);
        mask |= 1 << x;
    }
    return stk.join('');
}
