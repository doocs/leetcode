function fib(n: number): number {
    let a: number = 0,
        b: number = 1;
    for (let i: number = 0; i < n; i++) {
        let c: number = (a + b) % 1000000007;
        [a, b] = [b, c];
    }
    return a;
}
