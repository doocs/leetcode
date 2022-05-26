function fib(n: number): number {
    let a = 0;
    let b = 1;
    for (let i = 0; i < n; i++) {
        [a, b] = [a, a + b];
    }
    return a;
}
