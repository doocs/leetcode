function minimumChairs(s: string): number {
    let [cnt, left] = [0, 0];
    for (const c of s) {
        if (c === 'E') {
            if (left > 0) {
                --left;
            } else {
                ++cnt;
            }
        } else {
            ++left;
        }
    }
    return cnt;
}
