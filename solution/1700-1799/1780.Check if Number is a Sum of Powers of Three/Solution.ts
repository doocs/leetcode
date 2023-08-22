function checkPowersOfThree(n: number): boolean {
    while (n) {
        if (n % 3 > 1) return false;
        n = Math.floor(n / 3);
    }
    return true;
}
