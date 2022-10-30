function magicalString(n: number): number {
    const cs = [...'1221121'];
    let i = 5;
    while (cs.length < n) {
        const c = cs[cs.length - 1];
        cs.push(c === '1' ? '2' : '1');
        if (cs[i] !== '1') {
            cs.push(c === '1' ? '2' : '1');
        }
        i++;
    }
    return cs.slice(0, n).reduce((r, c) => r + (c === '1' ? 1 : 0), 0);
}
