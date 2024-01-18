function chalkReplacer(chalk: number[], k: number): number {
    let s = 0;
    for (const x of chalk) {
        s += x;
    }
    k %= s;
    for (let i = 0; ; ++i) {
        if (k < chalk[i]) {
            return i;
        }
        k -= chalk[i];
    }
}
