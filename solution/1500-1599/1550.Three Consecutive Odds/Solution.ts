function threeConsecutiveOdds(arr: number[]): boolean {
    let cnt = 0;
    for (const v of arr) {
        if (v & 1) {
            ++cnt;
        } else {
            cnt = 0;
        }
        if (cnt == 3) {
            return true;
        }
    }
    return false;
}
