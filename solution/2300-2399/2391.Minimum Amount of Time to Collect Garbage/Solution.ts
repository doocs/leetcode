function garbageCollection(garbage: string[], travel: number[]): number {
    const n = garbage.length;
    const count = [0, 0, 0];
    const cs = ['G', 'P', 'M'];
    for (const s of garbage) {
        for (const c of s) {
            if (c === 'G') {
                count[0]++;
            } else if (c === 'P') {
                count[1]++;
            } else if (c === 'M') {
                count[2]++;
            }
        }
    }

    let res = 0;
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < n; j++) {
            const s = garbage[j];
            for (const c of s) {
                if (c === cs[i]) {
                    res++;
                    count[i]--;
                }
            }
            if (count[i] === 0) {
                break;
            }
            res += travel[j];
        }
    }
    return res;
}
