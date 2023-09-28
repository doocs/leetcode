function largestGoodInteger(num: string): string {
    for (let i = 9; i >= 0; i--) {
        const s = String(i).repeat(3);
        if (num.includes(s)) {
            return s;
        }
    }
    return '';
}
