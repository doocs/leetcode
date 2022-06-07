function permutation(s: string): string[] {
    const n = s.length;
    const cs = s.split('');
    const set = new Set<string>();
    const dfs = (i: number) => {
        if (i === n) {
            set.add(cs.join(''));
            return;
        }
        dfs(i + 1);
        for (let j = i + 1; j < n; j++) {
            [cs[i], cs[j]] = [cs[j], cs[i]];
            dfs(i + 1);
            [cs[i], cs[j]] = [cs[j], cs[i]];
        }
    };
    dfs(0);
    return [...set];
}
