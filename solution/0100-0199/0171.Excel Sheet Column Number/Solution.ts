function titleToNumber(columnTitle: string): number {
    let ans: number = 0;
    for (const c of columnTitle) {
        ans = ans * 26 + (c.charCodeAt(0) - 'A'.charCodeAt(0) + 1);
    }
    return ans;
}
