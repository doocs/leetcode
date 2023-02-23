function maximumGroups(grades: number[]): number {
    const n = grades.length;
    let l = 1;
    let r = n;
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (mid * mid + mid > n * 2) {
            r = mid - 1;
        } else {
            l = mid;
        }
    }
    return l;
}
