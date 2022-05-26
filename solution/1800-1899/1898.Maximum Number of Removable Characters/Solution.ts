function maximumRemovals(s: string, p: string, removable: number[]): number {
    let left = 0,
        right = removable.length;
    while (left < right) {
        let mid = (left + right + 1) >> 1;
        if (isSub(s, p, new Set(removable.slice(0, mid)))) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}

function isSub(str: string, sub: string, idxes: Set<number>): boolean {
    let m = str.length,
        n = sub.length;
    let i = 0,
        j = 0;
    while (i < m && j < n) {
        if (!idxes.has(i) && str.charAt(i) == sub.charAt(j)) {
            ++j;
        }
        ++i;
    }
    return j == n;
}
