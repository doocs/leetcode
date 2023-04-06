function isRobotBounded(instructions: string): boolean {
    const direction = new Array(4).fill(0);
    let cur = 0;
    for (const c of instructions.split('')) {
        if (c === 'L') {
            cur = (cur + 1) % 4;
        } else if (c === 'R') {
            cur = (cur + 3) % 4;
        } else {
            ++direction[cur];
        }
    }
    return (
        cur !== 0 ||
        (direction[0] === direction[2] && direction[1] === direction[3])
    );
}
