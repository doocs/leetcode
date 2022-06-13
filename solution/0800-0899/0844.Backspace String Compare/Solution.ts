function backspaceCompare(s: string, t: string): boolean {
    let i = s.length - 1;
    let j = t.length - 1;
    while (i >= 0 || j >= 0) {
        let skip = 0;
        while (i >= 0) {
            if (s[i] === '#') {
                skip++;
            } else if (skip !== 0) {
                skip--;
            } else {
                break;
            }
            i--;
        }
        skip = 0;
        while (j >= 0) {
            if (t[j] === '#') {
                skip++;
            } else if (skip !== 0) {
                skip--;
            } else {
                break;
            }
            j--;
        }
        if (s[i] !== t[j]) {
            return false;
        }
        i--;
        j--;
    }
    return true;
}
