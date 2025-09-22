function compareVersion(version1: string, version2: string): number {
    const [m, n] = [version1.length, version2.length];
    let [i, j] = [0, 0];
    while (i < m || j < n) {
        let [a, b] = [0, 0];
        while (i < m && version1[i] !== '.') {
            a = a * 10 + +version1[i];
            i++;
        }
        while (j < n && version2[j] !== '.') {
            b = b * 10 + +version2[j];
            j++;
        }
        if (a !== b) {
            return a < b ? -1 : 1;
        }
        i++;
        j++;
    }
    return 0;
}
