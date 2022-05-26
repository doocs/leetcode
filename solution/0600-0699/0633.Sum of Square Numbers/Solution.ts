function judgeSquareSum(c: number): boolean {
    let a = 0,
        b = Math.floor(Math.sqrt(c));
    while (a <= b) {
        let sum = a ** 2 + b ** 2;
        if (sum == c) return true;
        if (sum < c) {
            ++a;
        } else {
            --b;
        }
    }
    return false;
}
