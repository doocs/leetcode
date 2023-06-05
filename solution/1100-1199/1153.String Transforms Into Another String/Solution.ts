function canConvert(str1: string, str2: string): boolean {
    if (str1 === str2) {
        return true;
    }
    if (new Set(str2).size === 26) {
        return false;
    }
    const d: Map<string, string> = new Map();
    for (const [i, c] of str1.split('').entries()) {
        if (!d.has(c)) {
            d.set(c, str2[i]);
        } else if (d.get(c) !== str2[i]) {
            return false;
        }
    }
    return true;
}
