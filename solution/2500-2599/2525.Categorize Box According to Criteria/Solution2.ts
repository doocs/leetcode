function categorizeBox(length: number, width: number, height: number, mass: number): string {
    const v = length * width * height;
    const bulky = length >= 1e4 || width >= 1e4 || height >= 1e4 || v >= 1e9;
    const heavy = mass >= 100;
    if (bulky && heavy) {
        return 'Both';
    }
    if (bulky) {
        return 'Bulky';
    }
    if (heavy) {
        return 'Heavy';
    }
    return 'Neither';
}
