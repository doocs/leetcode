const MX = 50;
const c: bigint[][] = Array.from({ length: MX }, () => Array(MX + 1).fill(0n));

for (let i = 0; i < MX; i++) {
    c[i][0] = 1n;
    for (let j = 1; j <= i; j++) {
        c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
    }
}

function nthSmallest(n: number, k: number): number {
    let nn = BigInt(n);
    let ans = 0n;
    for (let i = 49; i >= 0; i--) {
        if (nn > c[i][k]) {
            nn -= c[i][k];
            ans |= 1n << BigInt(i);
            if (--k === 0) {
                break;
            }
        }
    }
    return Number(ans);
}
