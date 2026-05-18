function countKthRoots(l: number, r: number, k: number): number {
    if (k === 1) {
        return r - l + 1;
    }
    let ans = 0;
    for (let x = 0; ; x++) {
        let y = 1;
        for (let i = 0; i < k; i++) {
            y *= x;
            if (y > r) {
                break;
            }
        }
        if (y > r) {
            break;
        }
        if (l <= y && y <= r) {
            ans++;
        }
    }
    return ans;
}
