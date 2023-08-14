function carFleet(target: number, position: number[], speed: number[]): number {
    const n = position.length;
    const idx = Array(n)
        .fill(0)
        .map((_, i) => i)
        .sort((i, j) => position[j] - position[i]);
    let ans = 0;
    let pre = 0;
    for (const i of idx) {
        const t = (target - position[i]) / speed[i];
        if (t > pre) {
            ++ans;
            pre = t;
        }
    }
    return ans;
}
