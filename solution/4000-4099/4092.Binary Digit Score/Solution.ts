function calculateScore(a: number, b: number): number {
    const s1 = a.toString(2).replace(/0/g, '').length;
    const s2 = b.toString(2).replace(/0/g, '').length;
    return s1 >= s2 ? s1 * s2 : Math.floor(s2 / s1);
}
