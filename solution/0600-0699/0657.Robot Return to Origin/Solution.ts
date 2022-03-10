function judgeCircle(moves: string): boolean {
    let x = 0,
        y = 0;
    const dir = {
        R: [1, 0],
        L: [-1, 0],
        U: [0, 1],
        D: [0, -1],
    };
    for (let u of moves) {
        const [dx, dy] = dir[u];
        x += dx;
        y += dy;
    }
    return !x && !y;
}
