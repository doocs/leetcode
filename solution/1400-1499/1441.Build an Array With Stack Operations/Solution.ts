function buildArray(target: number[], n: number): string[] {
    const res = [];
    let cur = 0;
    for (const num of target) {
        while (++cur < num) {
            res.push('Push', 'Pop');
        }
        res.push('Push');
    }
    return res;
}
