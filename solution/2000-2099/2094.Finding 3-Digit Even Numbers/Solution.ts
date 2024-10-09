function findEvenNumbers(digits: number[]): number[] {
    const cnt: number[] = Array(10).fill(0);
    for (const x of digits) {
        ++cnt[x];
    }
    const ans: number[] = [];
    for (let x = 100; x < 1000; x += 2) {
        const cnt1: number[] = Array(10).fill(0);
        for (let y = x; y; y = Math.floor(y / 10)) {
            ++cnt1[y % 10];
        }
        let ok = true;
        for (let i = 0; i < 10 && ok; ++i) {
            ok = cnt[i] >= cnt1[i];
        }
        if (ok) {
            ans.push(x);
        }
    }
    return ans;
}
