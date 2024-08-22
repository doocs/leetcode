function minimumDeletions(s: string): number {
    let ra = [...s].reduce((acc, x) => (x === 'a' ? acc + 1 : acc), 0);
    let lb = 0;

    let ans = s.length;
    for (const ch of s) {
        if (ch === 'a') ra--;
        ans = Math.min(ans, lb + ra);
        if (ch === 'b') lb++;
    }
    return ans;
}
