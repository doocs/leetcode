function maxDiff(num: number): number {
    let a = num.toString();
    let b = a;
    for (let i = 0; i < a.length; ++i) {
        if (a[i] !== '9') {
            a = a.split(a[i]).join('9');
            break;
        }
    }
    if (b[0] !== '1') {
        b = b.split(b[0]).join('1');
    } else {
        for (let i = 1; i < b.length; ++i) {
            if (b[i] !== '0' && b[i] !== '1') {
                b = b.split(b[i]).join('0');
                break;
            }
        }
    }
    return +a - +b;
}
