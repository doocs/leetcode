var minimumSwap = function (s1, s2) {
    let xy = 0,
        yx = 0;
    for (let i = 0; i < s1.length; ++i) {
        const a = s1[i],
            b = s2[i];
        if (a < b) {
            ++xy;
        }
        if (a > b) {
            ++yx;
        }
    }
    if ((xy + yx) % 2 === 1) {
        return -1;
    }
    return Math.floor(xy / 2) + Math.floor(yx / 2) + (xy % 2) + (yx % 2);
};
