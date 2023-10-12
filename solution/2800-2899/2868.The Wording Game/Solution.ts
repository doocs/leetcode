function canAliceWin(a: string[], b: string[]): boolean {
    let [i, j, k] = [1, 0, 1];
    let w = a[0];
    while (1) {
        if (k) {
            if (j === b.length) {
                return true;
            }
            if ((b[j][0] === w[0] && w < b[j]) || b[j].charCodeAt(0) - w.charCodeAt(0) === 1) {
                w = b[j];
                k ^= 1;
            }
            ++j;
        } else {
            if (i === a.length) {
                return false;
            }
            if ((a[i][0] === w[0] && w < a[i]) || a[i].charCodeAt(0) - w.charCodeAt(0) === 1) {
                w = a[i];
                k ^= 1;
            }
            ++i;
        }
    }
}
