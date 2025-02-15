function canPermutePalindrome(s: string): boolean {
    const vis = new Set<string>();
    for (const c of s) {
        if (vis.has(c)) {
            vis.delete(c);
        } else {
            vis.add(c);
        }
    }
    return vis.size < 2;
}
