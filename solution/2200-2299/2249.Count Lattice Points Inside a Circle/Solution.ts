function countLatticePoints(circles: number[][]): number {
    let mx = 0;
    let my = 0;
    for (const [x, y, r] of circles) {
        mx = Math.max(mx, x + r);
        my = Math.max(my, y + r);
    }
    let ans = 0;
    for (let i = 0; i <= mx; ++i) {
        for (let j = 0; j <= my; ++j) {
            for (const [x, y, r] of circles) {
                const dx = i - x;
                const dy = j - y;
                if (dx * dx + dy * dy <= r * r) {
                    ++ans;
                    break;
                }
            }
        }
    }
    return ans;
}
