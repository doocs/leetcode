function isCovered(ranges: number[][], left: number, right: number): boolean {
    const diff: number[] = Array(52).fill(0);
    for (const [l, r] of ranges) {
        ++diff[l];
        --diff[r + 1];
    }
    let s = 0;
    for (let i = 0; i < diff.length; ++i) {
        s += diff[i];
        if (s <= 0 && left <= i && i <= right) {
            return false;
        }
    }
    return true;
}
