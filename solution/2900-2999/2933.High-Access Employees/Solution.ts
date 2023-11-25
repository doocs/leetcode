function findHighAccessEmployees(access_times: string[][]): string[] {
    const d: Map<string, number[]> = new Map();
    for (const [name, s] of access_times) {
        const h = parseInt(s.slice(0, 2), 10);
        const m = parseInt(s.slice(2), 10);
        const t = h * 60 + m;
        if (!d.has(name)) {
            d.set(name, []);
        }
        d.get(name)!.push(t);
    }
    const ans: string[] = [];
    for (const [name, ts] of d) {
        ts.sort((a, b) => a - b);
        for (let i = 2; i < ts.length; ++i) {
            if (ts[i] - ts[i - 2] < 60) {
                ans.push(name);
                break;
            }
        }
    }
    return ans;
}
