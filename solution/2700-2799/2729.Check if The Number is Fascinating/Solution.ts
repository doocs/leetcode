function isFascinating(n: number): boolean {
    const s = `${n}${n * 2}${n * 3}`;
    return s.split('').sort().join('') === '123456789';
}
