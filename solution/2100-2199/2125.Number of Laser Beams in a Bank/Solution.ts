function numberOfBeams(bank: string[]): number {
    let [ans, pre] = [0, 0];
    for (const row of bank) {
        const cur = row.split('1').length - 1;
        if (cur) {
            ans += pre * cur;
            pre = cur;
        }
    }
    return ans;
}
