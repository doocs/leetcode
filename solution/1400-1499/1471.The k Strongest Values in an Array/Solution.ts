function getStrongest(arr: number[], k: number): number[] {
    arr.sort((a, b) => a - b);
    const m = arr[(arr.length - 1) >> 1];
    return arr.sort((a, b) => Math.abs(b - m) - Math.abs(a - m) || b - a).slice(0, k);
}
