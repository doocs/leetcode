function judgeCircle(moves: string): boolean {
    let [x, y] = [0, 0];
    for (const c of moves) {
        if (c === 'U') {
            y++;
        } else if (c === 'D') {
            y--;
        } else if (c === 'L') {
            x--;
        } else {
            x++;
        }
    }
    return x === 0 && y === 0;
}
