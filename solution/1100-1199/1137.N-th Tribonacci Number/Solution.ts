function tribonacci(n: number): number {
    let [a, b, c] = [0, 1, 1];
    while (n--) {
        let d = a + b + c;
        a = b;
        b = c;
        c = d;
    }
    return a;
}
