function generateTheString(n: number): string {
    const res = new Array(n).fill('a');
    if (n % 2 === 0) {
        res[n - 1] = 'b';
    }
    return res.join('');
}
