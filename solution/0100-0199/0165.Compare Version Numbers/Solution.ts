function compareVersion(version1: string, version2: string): number {
    let v1 = version1.split('.'),
        v2 = version2.split('.');
    for (let i = 0; i < Math.max(v1.length, v2.length); i++) {
        let c1 = Number(v1[i] || 0),
            c2 = Number(v2[i] || 0);
        if (c1 > c2) return 1;
        if (c1 < c2) return -1;
    }
    return 0;
}
