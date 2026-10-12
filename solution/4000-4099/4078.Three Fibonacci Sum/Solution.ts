const f = new Set<number>();

for (let a = 0, b = 1; b < 1000000001;) {
    [a, b] = [b, a + b];
    f.add(b);
}

function threeFibonacciSum(n: number): boolean {
    return n % 2 === 0 && f.has(n / 2);
}
