function countTime(time: string): number {
    let [hh, mm] = time.split(':');
    return count(hh, 24) * count(mm, 60);
}

function count(str: string, limit: number): number {
    let [a, b] = str.split('').map(d => Number(d));
    let ans = 0;
    if (isNaN(a) && isNaN(b)) return limit;
    if (isNaN(a)) {
        for (let i = 0; i <= 9; i++) {
            if (i * 10 + b < limit) ans++;
        }
        return ans;
    }
    if (isNaN(b)) {
        for (let i = 0; i <= 9; i++) {
            if (a * 10 + i < limit) ans++;
        }
        return ans;
    }
    return 1;
}
