function mostFrequentPrime(mat: number[][]): number {
    const m: number = mat.length;
    const n: number = mat[0].length;
    const cnt: Map<number, number> = new Map();
    const isPrime = (x: number): boolean => {
        for (let i = 2; i <= x / i; ++i) {
            if (x % i === 0) {
                return false;
            }
        }
        return true;
    };

    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let a = -1; a <= 1; ++a) {
                for (let b = -1; b <= 1; ++b) {
                    if (a === 0 && b === 0) {
                        continue;
                    }
                    let [x, y, v] = [i + a, j + b, mat[i][j]];
                    while (x >= 0 && x < m && y >= 0 && y < n) {
                        v = v * 10 + mat[x][y];
                        if (isPrime(v)) {
                            cnt.set(v, (cnt.get(v) || 0) + 1);
                        }
                        x += a;
                        y += b;
                    }
                }
            }
        }
    }

    let [ans, mx] = [-1, 0];
    cnt.forEach((x, v) => {
        if (mx < x || (mx === x && ans < v)) {
            mx = x;
            ans = v;
        }
    });
    return ans;
}
