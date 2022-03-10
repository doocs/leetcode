function construct2DArray(
    original: number[],
    m: number,
    n: number,
): number[][] {
    const result = [];

    if (original.length != m * n) {
        return result;
    }

    for (let i = 0; i < m; i++) {
        result.push(original.slice(i * n, i * n + n));
    }

    return result;
}
