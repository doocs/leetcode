function convert(s: string, numRows: number): string {
    if (numRows === 1) {
        return s;
    }
    const ss = new Array(numRows).fill('');
    let i = 0;
    let toDown = true;
    for (const c of s) {
        ss[i] += c;
        if (toDown) {
            i++;
        } else {
            i--;
        }
        if (i === 0 || i === numRows - 1) {
            toDown = !toDown;
        }
    }
    return ss.reduce((r, s) => r + s);
}
