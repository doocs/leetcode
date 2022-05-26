function canPermutePalindrome(s: string): boolean {
    const set = new Set<string>();
    for (const c of s) {
        if (set.has(c)) {
            set.delete(c);
        } else {
            set.add(c);
        }
    }
    return set.size <= 1;
}
