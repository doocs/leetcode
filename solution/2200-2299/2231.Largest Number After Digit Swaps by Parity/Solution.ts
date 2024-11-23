function largestInteger(num: number): number {
    const s = num.toString().split('');
    const cnt = Array(10).fill(0);

    for (const c of s) {
        cnt[+c]++;
    }

    const idx = [8, 9];
    let ans = 0;

    for (const c of s) {
        const x = +c;
        while (cnt[idx[x % 2]] === 0) {
            idx[x % 2] -= 2;
        }
        ans = ans * 10 + idx[x % 2];
        cnt[idx[x % 2]]--;
    }

    return ans;
}
