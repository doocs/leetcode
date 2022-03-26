function addBinary(a: string, b: string): string {
    const res = [];
    let i = a.length - 1;
    let j = b.length - 1;
    let isOver = false;
    while (i >= 0 || j >= 0 || isOver) {
        let sum = Number(a[i] || 0) + Number(b[j] || 0) + (isOver ? 1 : 0);
        isOver = false;
        if (sum > 1) {
            isOver = true;
            sum -= 2;
        }
        res.push(sum);
        i--;
        j--;
    }
    return res.reverse().join('');
}
