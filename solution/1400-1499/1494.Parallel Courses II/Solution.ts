function minNumberOfSemesters(n: number, relations: number[][], k: number): number {
    const prerequisites = Array(n + 1).fill(0);
    for (const [before, after] of relations) {
        prerequisites[after] |= 1 << before;
    }
    const size = 1 << (n + 1);
    const popcount = new Uint8Array(size);
    for (let mask = 1; mask < size; ++mask) {
        popcount[mask] = popcount[mask >> 1] + (mask & 1);
    }
    const seen = new Uint8Array(size);
    seen[0] = 1;
    const queue: [number, number][] = [[0, 0]];
    const target = size - 2;
    for (let head = 0; head < queue.length; ++head) {
        const [done, semesters] = queue[head];
        if (done === target) {
            return semesters;
        }
        let available = 0;
        for (let course = 1; course <= n; ++course) {
            if (
                !(done & (1 << course)) &&
                (done & prerequisites[course]) === prerequisites[course]
            ) {
                available |= 1 << course;
            }
        }
        if (popcount[available] <= k) {
            const next = done | available;
            if (!seen[next]) {
                seen[next] = 1;
                queue.push([next, semesters + 1]);
            }
        } else {
            for (let take = available; take; take = (take - 1) & available) {
                if (popcount[take] === k && !seen[done | take]) {
                    seen[done | take] = 1;
                    queue.push([done | take, semesters + 1]);
                }
            }
        }
    }
    return 0;
}
