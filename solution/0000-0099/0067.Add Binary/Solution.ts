function addBinary(a: string, b: string): string {
    const n = Math.max(a.length, b.length);
    const res = [];
    let isOver = false;
    for (let i = 0; i < n || isOver; i++) {
        let val = isOver ? 1 : 0;
        isOver = false;
        if (a[a.length - i - 1] === '1') {
            val++;
        }
        if (b[b.length - i - 1] === '1') {
            val++;
        }
        if (val > 1) {
            isOver = true;
            val -= 2;
        }
        res.push(val);
    }
    return res.reverse().join('');
}
