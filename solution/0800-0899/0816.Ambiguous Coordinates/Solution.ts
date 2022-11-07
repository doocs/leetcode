function ambiguousCoordinates(s: string): string[] {
    s = s.slice(1, s.length - 1);
    const n = s.length;
    const dfs = (s: string) => {
        const res: string[] = [];
        for (let i = 1; i < s.length; i++) {
            const t = `${s.slice(0, i)}.${s.slice(i)}`;
            if (`${Number(t)}` === t) {
                res.push(t);
            }
        }
        if (`${Number(s)}` === s) {
            res.push(s);
        }
        return res;
    };
    const ans: string[] = [];
    for (let i = 1; i < n; i++) {
        for (const left of dfs(s.slice(0, i))) {
            for (const right of dfs(s.slice(i))) {
                ans.push(`(${left}, ${right})`);
            }
        }
    }
    return ans;
}
