function validDigit(n: number, x: number): boolean {
    let hasX: boolean = false;
    while (n > 9) {
        hasX = hasX || n % 10 === x;
        n = Math.floor(n / 10);
    }
    return hasX && n !== x;
}
