function checkTwoChessboards(coordinate1: string, coordinate2: string): boolean {
    const x = coordinate1.charCodeAt(0) - coordinate2.charCodeAt(0);
    const y = coordinate1.charCodeAt(1) - coordinate2.charCodeAt(1);
    return (x + y) % 2 === 0;
}
