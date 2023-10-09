function getXORSum(arr1: number[], arr2: number[]): number {
    const a = arr1.reduce((acc, x) => acc ^ x);
    const b = arr2.reduce((acc, x) => acc ^ x);
    return a & b;
}
