function smallestSufficientTeam(
    req_skills: string[],
    people: string[][],
): number[] {
    const d: Map<string, number> = new Map();
    const m = req_skills.length;
    const n = people.length;
    for (let i = 0; i < m; ++i) {
        d.set(req_skills[i], i);
    }
    const p: number[] = new Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        for (const s of people[i]) {
            p[i] |= 1 << d.get(s)!;
        }
    }
    const inf = 1 << 30;
    const f: number[] = new Array(1 << m).fill(inf);
    const g: number[] = new Array(1 << m).fill(0);
    const h: number[] = new Array(1 << m).fill(0);
    f[0] = 0;
    for (let i = 0; i < 1 << m; ++i) {
        if (f[i] === inf) {
            continue;
        }
        for (let j = 0; j < n; ++j) {
            if (f[i] + 1 < f[i | p[j]]) {
                f[i | p[j]] = f[i] + 1;
                g[i | p[j]] = j;
                h[i | p[j]] = i;
            }
        }
    }
    const ans: number[] = [];
    for (let i = (1 << m) - 1; i; i = h[i]) {
        ans.push(g[i]);
    }
    return ans;
}
