function countSeniors(details: string[]): number {
    let ans = 0;
    for (const x of details) {
        const age = parseInt(x.slice(11, 13));
        if (age > 60) {
            ++ans;
        }
    }
    return ans;
}
