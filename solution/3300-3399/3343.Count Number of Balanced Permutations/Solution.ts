const MX = 80;
const MOD = 10 ** 9 + 7;
const c: number[][] = Array.from({ length: MX }, () => Array(MX).fill(0));
(function init() {
    c[0][0] = 1;
    for (let i = 1; i < MX; i++) {
        c[i][0] = 1;
        for (let j = 1; j <= i; j++) {
            c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % MOD;
        }
    }
})();

function countBalancedPermutations(num: string): number {
    const cnt = Array(10).fill(0);
    let s = 0;
    for (const ch of num) {
        cnt[+ch]++;
        s += +ch;
    }

    if (s % 2 !== 0) {
        return 0;
    }

    const n = num.length;
    const m = Math.floor(n / 2) + 1;
    const f: Record<string, number> = {};

    const dfs = (i: number, j: number, a: number, b: number): number => {
        if (i > 9) {
            return (j | a | b) === 0 ? 1 : 0;
        }
        if (a === 0 && j > 0) {
            return 0;
        }

        const key = `${i},${j},${a},${b}`;
        if (key in f) {
            return f[key];
        }

        let ans = 0;
        for (let l = 0; l <= Math.min(cnt[i], a); l++) {
            const r = cnt[i] - l;
            if (r >= 0 && r <= b && l * i <= j) {
                const t = Number(
                    (((BigInt(c[a][l]) * BigInt(c[b][r])) % BigInt(MOD)) *
                        BigInt(dfs(i + 1, j - l * i, a - l, b - r))) %
                        BigInt(MOD),
                );
                ans = (ans + t) % MOD;
            }
        }
        f[key] = ans;
        return ans;
    };

    return dfs(0, s / 2, Math.floor(n / 2), Math.floor((n + 1) / 2));
}
