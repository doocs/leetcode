function compareVersion(version1: string, version2: string): number {
    const v1 = version1.split('.');
    const v2 = version2.split('.');
    for (let i = 0; i < Math.max(v1.length, v2.length); ++i) {
        const [n1, n2] = [+v1[i] || 0, +v2[i] || 0];
        if (n1 < n2) {
            return -1;
        }
        if (n1 > n2) {
            return 1;
        }
    }
    return 0;
}
