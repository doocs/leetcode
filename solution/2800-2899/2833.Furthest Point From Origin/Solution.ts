function furthestDistanceFromOrigin(moves: string): number {
    const count = (c: string) => moves.split('').filter(x => x === c).length;
    return Math.abs(count('L') - count('R')) + count('_');
}
