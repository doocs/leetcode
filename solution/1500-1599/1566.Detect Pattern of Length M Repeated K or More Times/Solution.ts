function containsPattern(arr: number[], m: number, k: number): boolean {
    if (arr.length < m * k) {
        return false;
    }
    const target = (k - 1) * m;
    let cnt = 0;
    for (let i = m; i < arr.length; ++i) {
        if (arr[i] === arr[i - m]) {
            if (++cnt === target) {
                return true;
            }
        } else {
            cnt = 0;
        }
    }
    return false;
}
