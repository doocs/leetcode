function oneEditAway(first: string, second: string): boolean {
    let m: number = first.length;
    let n: number = second.length;
    if (m < n) {
        return oneEditAway(second, first);
    }
    if (m - n > 1) {
        return false;
    }

    let cnt: number = 0;
    if (m === n) {
        for (let i: number = 0; i < n; ++i) {
            if (first[i] !== second[i]) {
                if (++cnt > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    for (let i: number = 0, j: number = 0; i < m; ++i) {
        if (j === n || (j < n && first[i] !== second[j])) {
            ++cnt;
        } else {
            ++j;
        }
    }
    return cnt < 2;
}
