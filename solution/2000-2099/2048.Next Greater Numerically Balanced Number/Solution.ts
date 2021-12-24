function nextBeautifulNumber(n: number): number {
    for (let ans = n + 1; ; ans++) {
        if (isValid(ans)) {
            return ans;
        }
    }
}

function isValid(n: number): boolean {
    let record = new Array(10).fill(0);
    while (n > 0) {
        const idx = n % 10;
        record[idx]++;
        n = Math.floor(n / 10);
    }
    for (let i = 0; i < 10; i++) {
        if (record[i] && record[i] != i) return false;
    }
    return true;
}
