function elevatorRequests(n: number, start: number, requests: number[][]): number {
    const m = requests.length;
    const f: number[][] = Array.from({ length: 1 << m }, () => Array(m).fill(0));

    for (let i = 0; i < 1 << m; i++) {
        for (let j = 0; j < m; j++) {
            if (((i >> j) & 1) === 1) {
                f[i][j] = Infinity;

                const i0 = i ^ (1 << j);

                if (i0 === 0) {
                    const d = Math.abs(start - requests[j][1]);

                    f[i][j] = Math.min(f[i][j], Math.max(d, requests[j][0]));
                } else {
                    for (let j0 = 0; j0 < m; j0++) {
                        if (j0 !== j && ((i >> j0) & 1) === 1) {
                            const d = Math.abs(requests[j0][1] - requests[j][1]);

                            f[i][j] = Math.min(f[i][j], Math.max(f[i0][j0] + d, requests[j][0]));
                        }
                    }
                }
            }
        }
    }

    const full = (1 << m) - 1;
    let ans = Infinity;

    for (let j = 0; j < m; j++) {
        ans = Math.min(ans, f[full][j]);
    }

    return ans;
}
