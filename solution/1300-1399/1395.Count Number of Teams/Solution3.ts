function numTeams(rating: number[]): number {
    const n = rating.length;
    const f: Record<Type, number[][]> = {
        asc: Array.from({ length: n }, () => Array(3).fill(-1)),
        desc: Array.from({ length: n }, () => Array(3).fill(-1)),
    };

    const fn = (i: number, available: number, type: Type) => {
        if (!available) {
            return 1;
        }
        if (f[type][i][available] !== -1) {
            return f[type][i][available];
        }

        let ans = 0;
        for (let j = i + 1; j < n; j++) {
            if (rating[j] > rating[i]) {
                if (type === 'asc') {
                    ans += fn(j, available - 1, 'asc');
                }
            } else {
                if (type === 'desc') {
                    ans += fn(j, available - 1, 'desc');
                }
            }
        }
        f[type][i][available] = ans;

        return ans;
    };

    let ans = 0;
    for (let i = 0; i < n; i++) {
        ans += fn(i, 2, 'asc') + fn(i, 2, 'desc');
    }

    return ans;
}

type Type = 'asc' | 'desc';
