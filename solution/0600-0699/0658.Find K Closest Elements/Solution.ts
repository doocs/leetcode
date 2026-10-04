function findClosestElements(arr: number[], k: number, x: number): number[] {
    arr.sort((a, b) => Math.abs(a - x) - Math.abs(b - x) || a - b);
    return arr.slice(0, k).sort((a, b) => a - b);
}
