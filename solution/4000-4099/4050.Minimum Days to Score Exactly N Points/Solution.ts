const MX = 100001;

const f = new Array<number>(MX).fill(Infinity);

f[0] = -1;

for (let i = 1; i < MX; i++) {
    for (let j = 1; (j * (j + 1)) / 2 <= i; j++) {
        const s = (j * (j + 1)) / 2;
        f[i] = Math.min(f[i], f[i - s] + j + 1);
    }
}

function minDays(n: number): number {
    return f[n];
}
