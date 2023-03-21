function minMaxDifference(num: number): number {
    const s = num + '';
    const min = Number(s.replace(new RegExp(s[0], 'g'), '0'));
    for (const c of s) {
        if (c !== '9') {
            return Number(s.replace(new RegExp(c, 'g'), '9')) - min;
        }
    }
    return num - min;
}
