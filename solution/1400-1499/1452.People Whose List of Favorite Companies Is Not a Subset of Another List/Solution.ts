function peopleIndexes(favoriteCompanies: string[][]): number[] {
    const n = favoriteCompanies.length;
    const d: Map<string, number> = new Map();
    let idx = 0;
    const nums: Set<number>[] = Array.from({ length: n }, () => new Set<number>());

    for (let i = 0; i < n; i++) {
        for (const s of favoriteCompanies[i]) {
            if (!d.has(s)) {
                d.set(s, idx++);
            }
            nums[i].add(d.get(s)!);
        }
    }

    const check = (a: Set<number>, b: Set<number>): boolean => {
        for (const x of a) {
            if (!b.has(x)) {
                return false;
            }
        }
        return true;
    };

    const ans: number[] = [];
    for (let i = 0; i < n; i++) {
        let ok = true;
        for (let j = 0; j < n && ok; j++) {
            if (i !== j && check(nums[i], nums[j])) {
                ok = false;
            }
        }
        if (ok) {
            ans.push(i);
        }
    }

    return ans;
}
