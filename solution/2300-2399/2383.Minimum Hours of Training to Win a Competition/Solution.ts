function minNumberOfHours(x: number, y: number, energy: number[], experience: number[]): number {
    let ans = 0;
    for (let i = 0; i < energy.length; ++i) {
        const [dx, dy] = [energy[i], experience[i]];
        if (x <= dx) {
            ans += dx + 1 - x;
            x = dx + 1;
        }
        if (y <= dy) {
            ans += dy + 1 - y;
            y = dy + 1;
        }
        x -= dx;
        y += dy;
    }
    return ans;
}
