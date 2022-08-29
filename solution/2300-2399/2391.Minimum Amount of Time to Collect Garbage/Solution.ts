function garbageCollection(garbage: string[], travel: number[]): number {
    let ans = 0;
    let pos = new Map();
    for (let i = 0; i < garbage.length; ++i) {
        ans += garbage[i].length;
        for (const c of garbage[i]) {
            pos.set(c, i);
        }
    }
    let s = new Array(travel.length + 1).fill(0);
    for (let i = 0; i < travel.length; ++i) {
        s[i + 1] = s[i] + travel[i];
    }
    for (const [_, i] of pos) {
        ans += s[i];
    }
    return ans;
}
