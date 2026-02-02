function bestTower(towers: number[][], center: number[], radius: number): number[] {
    const [cx, cy] = center;
    let idx = -1;
    for (let i = 0; i < towers.length; i++) {
        const [x, y, q] = towers[i];
        const dist = Math.abs(x - cx) + Math.abs(y - cy);
        if (dist > radius) {
            continue;
        }
        if (
            idx === -1 ||
            towers[idx][2] < q ||
            (towers[idx][2] === q &&
                (x < towers[idx][0] || (x === towers[idx][0] && y < towers[idx][1])))
        ) {
            idx = i;
        }
    }
    return idx === -1 ? [-1, -1] : [towers[idx][0], towers[idx][1]];
}
