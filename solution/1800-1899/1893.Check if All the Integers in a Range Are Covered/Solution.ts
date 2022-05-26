function isCovered(ranges: number[][], left: number, right: number): boolean {
    let diff = new Array(52).fill(0);
    for (let [start, end] of ranges) {
        ++diff[start];
        --diff[end + 1];
    }
    let cur = 0;
    for (let i = 1; i <= 50; i++) {
        cur += diff[i];
        if (i >= left && i <= right && cur <= 0) {
            return false;
        }
    }
    return true;
}
