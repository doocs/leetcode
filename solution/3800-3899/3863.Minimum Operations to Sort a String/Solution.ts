function minOperations(s: string): number {
    const n = s.length;

    let sorted = true;
    for (let i = 1; i < n; i++) {
        if (s[i] < s[i - 1]) {
            sorted = false;
            break;
        }
    }

    if (sorted) {
        return 0;
    }

    if (n === 2) {
        return -1;
    }

    let mn = s[0];
    let mx = s[0];

    for (const c of s) {
        if (c < mn) {
            mn = c;
        }
        if (c > mx) {
            mx = c;
        }
    }

    if (s[0] === mn || s[n - 1] === mx) {
        return 1;
    }

    for (let i = 1; i < n - 1; i++) {
        if (s[i] === mn || s[i] === mx) {
            return 2;
        }
    }

    return 3;
}
