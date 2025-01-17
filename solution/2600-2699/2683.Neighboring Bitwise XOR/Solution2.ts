function doesValidArrayExist(derived: number[]): boolean {
    return derived.reduce((a, b) => a + b, 0) % 2 === 0;
}
