function canChange(start: string, target: string): boolean {
    if (
        [...start].filter(c => c !== '_').join('') !==
        [...target].filter(c => c !== '_').join('')
    ) {
        return false;
    }
    const n = start.length;
    let i = 0;
    let j = 0;
    while (i < n || j < n) {
        while (start[i] === '_') {
            i++;
        }
        while (target[j] === '_') {
            j++;
        }
        if (start[i] === 'R') {
            if (i > j) {
                return false;
            }
        }
        if (start[i] === 'L') {
            if (i < j) {
                return false;
            }
        }
        i++;
        j++;
    }
    return true;
}
