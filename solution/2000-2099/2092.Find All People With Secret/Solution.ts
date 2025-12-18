function findAllPeople(n: number, meetings: number[][], firstPerson: number): number[] {
    const vis: boolean[] = Array(n).fill(false);
    vis[0] = true;
    vis[firstPerson] = true;

    meetings.sort((x, y) => x[2] - y[2]);

    for (let i = 0, m = meetings.length; i < m; ) {
        let j = i;
        while (j + 1 < m && meetings[j + 1][2] === meetings[i][2]) {
            ++j;
        }

        const g = new Map<number, number[]>();
        const s = new Set<number>();

        for (let k = i; k <= j; ++k) {
            const x = meetings[k][0];
            const y = meetings[k][1];

            if (!g.has(x)) g.set(x, []);
            if (!g.has(y)) g.set(y, []);

            g.get(x)!.push(y);
            g.get(y)!.push(x);

            s.add(x);
            s.add(y);
        }

        const q: number[] = [];
        for (const u of s) {
            if (vis[u]) {
                q.push(u);
            }
        }

        for (const u of q) {
            for (const v of g.get(u)!) {
                if (!vis[v]) {
                    vis[v] = true;
                    q.push(v);
                }
            }
        }

        i = j + 1;
    }

    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (vis[i]) {
            ans.push(i);
        }
    }
    return ans;
}
