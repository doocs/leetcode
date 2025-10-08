function minTime(skill: number[], mana: number[]): number {
    const n = skill.length;
    const f: number[] = Array(n).fill(0);
    for (const x of mana) {
        let tot = 0;
        for (let i = 0; i < n; ++i) {
            tot = Math.max(tot, f[i]) + skill[i] * x;
        }
        f[n - 1] = tot;
        for (let i = n - 2; i >= 0; --i) {
            f[i] = f[i + 1] - skill[i + 1] * x;
        }
    }
    return f[n - 1];
}
