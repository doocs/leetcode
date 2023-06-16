function pivotInteger(n: number): number {
    const y = Math.floor((n * (n + 1)) / 2);
    const x = Math.floor(Math.sqrt(y));
    return x * x === y ? x : -1;
}
