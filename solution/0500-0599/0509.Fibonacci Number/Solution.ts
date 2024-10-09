function fib(n: number): number {
    let [a, b] = [0, 1];
    while (n--) {
        [a, b] = [b, a + b];
    }
    return a;
}
