function lengthAfterTransformations(s: string, t: number, nums: number[]): number {
    const MOD = BigInt(1e9 + 7);
    const M = 26;

    const cnt: number[] = Array(M).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
    }

    const matrix: number[][] = Array.from({ length: M }, () => Array(M).fill(0));
    for (let i = 0; i < M; i++) {
        for (let j = 1; j <= nums[i]; j++) {
            matrix[i][(i + j) % M] = 1;
        }
    }

    const matmul = (a: number[][], b: number[][]): number[][] => {
        const n = a.length,
            p = b.length,
            q = b[0].length;
        const res: number[][] = Array.from({ length: n }, () => Array(q).fill(0));
        for (let i = 0; i < n; i++) {
            for (let k = 0; k < p; k++) {
                const aik = BigInt(a[i][k]);
                if (aik !== BigInt(0)) {
                    for (let j = 0; j < q; j++) {
                        const product = aik * BigInt(b[k][j]);
                        const sum = BigInt(res[i][j]) + product;
                        res[i][j] = Number(sum % MOD);
                    }
                }
            }
        }
        return res;
    };

    const matpow = (mat: number[][], power: number): number[][] => {
        let res: number[][] = Array.from({ length: M }, (_, i) =>
            Array.from({ length: M }, (_, j) => (i === j ? 1 : 0)),
        );
        while (power > 0) {
            if (power % 2 === 1) res = matmul(res, mat);
            mat = matmul(mat, mat);
            power = Math.floor(power / 2);
        }
        return res;
    };

    const cntMat: number[][] = [cnt.slice()];
    const factor = matpow(matrix, t);
    const result = matmul(cntMat, factor);

    let ans = 0n;
    for (const v of result[0]) {
        ans = (ans + BigInt(v)) % MOD;
    }
    return Number(ans);
}
