function countAsterisks(s: string): number {
    let ans = 0;
    let flag = true;
    for (const c of s) {
        if (c === '|') {
            flag = !flag;
        } else if (c === '*' && flag) {
            ans++;
        }
    }
    return ans;
}
