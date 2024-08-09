function survivedRobotsHealths(
    positions: number[],
    healths: number[],
    directions: string,
): number[] {
    const idx = Array.from({ length: positions.length }, (_, i) => i);
    const stk: number[] = [];

    idx.sort((a, b) => positions[a] - positions[b]);

    for (let iRight of idx) {
        while (stk.length) {
            const iLeft = stk.at(-1)!;
            const havePair = directions[iLeft] === 'R' && directions[iRight] === 'L';
            if (!havePair) break;

            if (healths[iLeft] === healths[iRight]) {
                healths[iLeft] = healths[iRight] = iRight = -1;
                stk.pop();
                break;
            }

            if (healths[iLeft] < healths[iRight]) {
                healths[iLeft] = -1;
                healths[iRight]--;
                stk.pop();
            } else {
                healths[iRight] = iRight = -1;
                healths[iLeft]--;
                break;
            }
        }

        if (iRight !== -1) stk.push(iRight);
    }

    return healths.filter(i => ~i);
}
