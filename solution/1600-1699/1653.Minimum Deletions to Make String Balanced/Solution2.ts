function minimumDeletions(s: string): number {
    let [ans, b] = [0, 0];

    for (const ch of s) {
        if (ch === 'b') {
            ++b;
        } else {
            ans = Math.min(ans + 1, b);
        }
    }
    return ans;
}
