function minimumSwap(s1: string, s2: string): number {
    let xy = 0,
        yx = 0;

    for (let i = 0; i < s1.length; ++i) {
        const a = s1[i],
            b = s2[i];
        xy += a < b ? 1 : 0;
        yx += a > b ? 1 : 0;
    }

    if ((xy + yx) % 2 !== 0) {
        return -1;
    }

    return Math.floor(xy / 2) + Math.floor(yx / 2) + (xy % 2) + (yx % 2);
}
