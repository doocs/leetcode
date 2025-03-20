function buildArray(target: number[], n: number): string[] {
    const ans: string[] = [];
    let cur: number = 1;
    for (const x of target) {
        for (; cur < x; ++cur) {
            ans.push('Push', 'Pop');
        }
        ans.push('Push');
        ++cur;
    }
    return ans;
}
