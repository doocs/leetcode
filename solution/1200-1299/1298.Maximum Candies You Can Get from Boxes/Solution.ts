function maxCandies(
    status: number[],
    candies: number[],
    keys: number[][],
    containedBoxes: number[][],
    initialBoxes: number[],
): number {
    const q: number[] = [];
    const has: Set<number> = new Set();
    const took: Set<number> = new Set();
    let ans = 0;

    for (const box of initialBoxes) {
        has.add(box);
        if (status[box] === 1) {
            q.push(box);
            took.add(box);
            ans += candies[box];
        }
    }

    while (q.length > 0) {
        const box = q.pop()!;

        for (const k of keys[box]) {
            if (status[k] === 0) {
                status[k] = 1;
                if (has.has(k) && !took.has(k)) {
                    q.push(k);
                    took.add(k);
                    ans += candies[k];
                }
            }
        }

        for (const b of containedBoxes[box]) {
            has.add(b);
            if (status[b] === 1 && !took.has(b)) {
                q.push(b);
                took.add(b);
                ans += candies[b];
            }
        }
    }

    return ans;
}
