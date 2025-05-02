function pushDominoes(dominoes: string): string {
    const n = dominoes.length;
    const q: number[] = [];
    const time: number[] = Array(n).fill(-1);
    const force: string[][] = Array.from({ length: n }, () => []);

    for (let i = 0; i < n; i++) {
        const f = dominoes[i];
        if (f !== '.') {
            q.push(i);
            time[i] = 0;
            force[i].push(f);
        }
    }

    const ans: string[] = Array(n).fill('.');
    let head = 0;
    while (head < q.length) {
        const i = q[head++];
        if (force[i].length === 1) {
            const f = force[i][0];
            ans[i] = f;
            const j = f === 'L' ? i - 1 : i + 1;
            if (j >= 0 && j < n) {
                const t = time[i];
                if (time[j] === -1) {
                    q.push(j);
                    time[j] = t + 1;
                    force[j].push(f);
                } else if (time[j] === t + 1) {
                    force[j].push(f);
                }
            }
        }
    }
    return ans.join('');
}
