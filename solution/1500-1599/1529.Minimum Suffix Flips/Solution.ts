function minFlips(target: string): number {
    let ans = 0;
    for (const c of target) {
        if (ans % 2 !== +c) {
            ++ans;
        }
    }
    return ans;
}
