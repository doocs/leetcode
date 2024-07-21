function losingPlayer(x: number, y: number): string {
    const k = Math.min((x / 2) | 0, (y / 8) | 0);
    x -= k * 2;
    y -= k * 8;
    return x && y >= 4 ? 'Alice' : 'Bob';
}
