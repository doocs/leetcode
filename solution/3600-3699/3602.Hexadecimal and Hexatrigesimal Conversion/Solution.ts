function concatHex36(n: number): string {
    function f(x: number, k: number): string {
        const digits = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        let res = '';
        while (x > 0) {
            const v = x % k;
            res = digits[v] + res;
            x = Math.floor(x / k);
        }
        return res;
    }

    const x = n * n;
    const y = n * n * n;
    return f(x, 16) + f(y, 36);
}
