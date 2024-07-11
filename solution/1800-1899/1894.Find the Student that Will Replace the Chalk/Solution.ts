function chalkReplacer(chalk: number[], k: number): number {
    const s = chalk.reduce((acc, cur) => acc + cur, 0);
    k %= s;
    for (let i = 0; ; ++i) {
        if (k < chalk[i]) {
            return i;
        }
        k -= chalk[i];
    }
}
