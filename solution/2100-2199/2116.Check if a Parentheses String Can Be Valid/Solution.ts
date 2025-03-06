function canBeValid(s: string, locked: string): boolean {
    const n = s.length;
    if (n & 1) {
        return false;
    }
    let x = 0;
    for (let i = 0; i < n; ++i) {
        if (s[i] === '(' || locked[i] === '0') {
            ++x;
        } else if (x > 0) {
            --x;
        } else {
            return false;
        }
    }
    x = 0;
    for (let i = n - 1; i >= 0; --i) {
        if (s[i] === ')' || locked[i] === '0') {
            ++x;
        } else if (x > 0) {
            --x;
        } else {
            return false;
        }
    }
    return true;
}
