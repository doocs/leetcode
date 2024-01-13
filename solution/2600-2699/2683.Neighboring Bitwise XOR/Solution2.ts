function doesValidArrayExist(derived: number[]): boolean {
    return derived.reduce((acc, x) => acc ^ x, 0) === 0;
}
