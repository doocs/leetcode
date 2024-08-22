function canBeEqual(target: number[], arr: number[]): boolean {
    target.sort();
    arr.sort();
    return target.every((x, i) => x === arr[i]);
}
