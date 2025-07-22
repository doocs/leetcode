function findLucky(arr: number[]): number {
    const cnt: number[] = Array(501).fill(0);
    for (const x of arr) {
        ++cnt[x];
    }
    for (let x = cnt.length - 1; x; --x) {
        if (x === cnt[x]) {
            return x;
        }
    }
    return -1;
}
