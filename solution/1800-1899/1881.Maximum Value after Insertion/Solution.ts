function maxValue(n: string, x: number): string {
    let i = 0;
    if (n[0] === '-') {
        i++;
        while (i < n.length && +n[i] <= x) {
            i++;
        }
    } else {
        while (i < n.length && +n[i] >= x) {
            i++;
        }
    }
    return n.slice(0, i) + x + n.slice(i);
}
