function maximumGain(s: string, x: number, y: number): number {
    const stk: string[] = [];
    const pairs: Record<string, string> = { a: 'b', b: 'a' };
    const pair = x > y ? ['a', 'b'] : ['b', 'a'];
    let str = [...s];
    let ans = 0;
    let havePairs = true;

    while (havePairs) {
        for (const p of pair) {
            havePairs = true;

            for (const ch of str) {
                if (stk.at(-1) === p && ch === pairs[p]) {
                    stk.pop();
                } else stk.push(ch);
            }

            if (str.length === stk.length) havePairs = false;

            const multiplier = p === 'a' ? x : y;
            ans += (multiplier * (str.length - stk.length)) / 2;
            str = [...stk];
            stk.length = 0;
        }
    }

    return ans;
}
