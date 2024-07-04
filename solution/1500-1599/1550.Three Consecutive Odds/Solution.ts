function threeConsecutiveOdds(arr: number[]): boolean {
    let cnt = 0;
    for (const x of arr) {
        if (x & 1) {
            if (++cnt == 3) {
                return true;
            }
        } else {
            cnt = 0;
        }
    }
    return false;
}
