function garbageCollection(garbage: string[], travel: number[]): number {
    const f = (x: string) => {
        let ans = 0;
        let st = 0;
        for (let i = 0; i < garbage.length; ++i) {
            let cnt = 0;
            for (const c of garbage[i]) {
                if (c === x) {
                    ++cnt;
                }
            }
            if (cnt > 0) {
                ans += cnt + st;
                st = 0;
            }
            if (i < travel.length) {
                st += travel[i];
            }
        }
        return ans;
    };
    return f('M') + f('P') + f('G');
}
