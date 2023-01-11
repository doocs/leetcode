function digitCount(num: string): boolean {
    const n = num.length;
    const count = new Array(10).fill(0);
    for (let i = 0; i < n; i++) {
        count[i] = Number(num[i]);
    }
    for (const c of num) {
        count[c]--;
    }
    return count.every(v => v === 0);
}
