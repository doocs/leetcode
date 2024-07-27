function digitCount(num: string): boolean {
    const cnt: number[] = Array(10).fill(0);
    for (const c of num) {
        ++cnt[+c];
    }
    for (let i = 0; i < num.length; ++i) {
        if (cnt[i] !== +num[i]) {
            return false;
        }
    }
    return true;
}
