function finalPositionOfSnake(n: number, commands: string[]): number {
    let [x, y] = [0, 0];
    for (const c of commands) {
        c[0] === 'U' && x--;
        c[0] === 'D' && x++;
        c[0] === 'L' && y--;
        c[0] === 'R' && y++;
    }
    return x * n + y;
}
