function largestGoodInteger(num: string): string {
    for (let i = 9; i >= 0; i--) {
        const c = String(i).repeat(3);
        if (num.includes(c)) return c;
    }
    return '';
}
