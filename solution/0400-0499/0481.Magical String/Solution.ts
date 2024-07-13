function magicalString(n: number): number {
    const s: number[] = [1, 2, 2];
    for (let i = 2; s.length < n; ++i) {
        let pre = s[s.length - 1];
        let cur = 3 - pre;
        for (let j = 0; j < s[i]; ++j) {
            s.push(cur);
        }
    }
    return s.slice(0, n).filter(x => x === 1).length;
}
