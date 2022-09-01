function queryString(s: string, n: number): boolean {
    for (let i = 1; i <= n; ++i) {
        if (s.indexOf(i.toString(2)) === -1) {
            return false;
        }
    }
    return true;
}
