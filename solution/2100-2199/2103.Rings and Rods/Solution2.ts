function countPoints(rings: string): number {
    let c = 0;
    for (let i = 0; i <= 9; i++) {
        if (rings.includes('B' + i) && rings.includes('R' + i) && rings.includes('G' + i)) c++;
    }
    return c;
}
