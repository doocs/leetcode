function averageHeightOfBuildings(buildings: number[][]): number[][] {
    const cnt = new Map<number, number>();
    const d = new Map<number, number>();
    for (const [start, end, height] of buildings) {
        cnt.set(start, (cnt.get(start) || 0) + 1);
        cnt.set(end, (cnt.get(end) || 0) - 1);
        d.set(start, (d.get(start) || 0) + height);
        d.set(end, (d.get(end) || 0) - height);
    }
    let [s, m] = [0, 0];
    let last = -1;
    const ans: number[][] = [];
    const sortedKeys = Array.from(d.keys()).sort((a, b) => a - b);
    for (const k of sortedKeys) {
        const v = d.get(k)!;
        if (m > 0) {
            const avg = Math.floor(s / m);
            if (ans.length > 0 && ans.at(-1)![2] === avg && ans.at(-1)![1] === last) {
                ans[ans.length - 1][1] = k;
            } else {
                ans.push([last, k, avg]);
            }
        }
        s += v;
        m += cnt.get(k)!;
        last = k;
    }
    return ans;
}
