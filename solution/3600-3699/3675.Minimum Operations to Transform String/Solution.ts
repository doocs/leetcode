function minOperations(s: string): number {
    let ans = 0;
    for (const c of s) {
        if (c !== 'a') {
            ans = Math.max(ans, 26 - (c.charCodeAt(0) - 97));
        }
    }
    return ans;
}
