function minMaxDifference(num: number): number {
    const s = num.toString();
    const mi = +s.replaceAll(s[0], '0');
    for (const c of s) {
        if (c !== '9') {
            const mx = +s.replaceAll(c, '9');
            return mx - mi;
        }
    }
    return num - mi;
}
