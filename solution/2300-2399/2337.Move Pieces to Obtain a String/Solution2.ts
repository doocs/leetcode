function canChange(start: string, target: string): boolean {
    const n = start.length;
    let [i, j] = [0, 0];
    while (1) {
        while (i < n && start[i] === '_') {
            ++i;
        }
        while (j < n && target[j] === '_') {
            ++j;
        }
        if (i === n && j === n) {
            return true;
        }
        if (i === n || j === n || start[i] !== target[j]) {
            return false;
        }
        if ((start[i] === 'L' && i < j) || (start[i] === 'R' && i > j)) {
            return false;
        }
        ++i;
        ++j;
    }
}
