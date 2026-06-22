function maxDistance(moves: string): number {
    let [x, y, z] = [0, 0, 0];
    for (const c of moves) {
        if (c === 'U') {
            x -= 1;
        } else if (c === 'D') {
            x += 1;
        } else if (c === 'L') {
            y -= 1;
        } else if (c === 'R') {
            y += 1;
        } else {
            z += 1;
        }
    }
    return Math.abs(x) + Math.abs(y) + z;
}
