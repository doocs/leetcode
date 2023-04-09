function isRobotBounded(instructions: string): boolean {
    const dist: number[] = new Array(4).fill(0);
    let k = 0;
    for (const c of instructions) {
        if (c === 'L') {
            k = (k + 1) % 4;
        } else if (c === 'R') {
            k = (k + 3) % 4;
        } else {
            ++dist[k];
        }
    }
    return (dist[0] === dist[2] && dist[1] === dist[3]) || k !== 0;
}
