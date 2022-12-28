function minimumLength(s: string): number {
    const n = s.length;
    let start = 0;
    let end = n - 1;
    while (start < end && s[start] === s[end]) {
        while (start + 1 < end && s[start] === s[start + 1]) {
            start++;
        }
        while (start < end - 1 && s[end] === s[end - 1]) {
            end--;
        }
        start++;
        end--;
    }
    return Math.max(0, end - start + 1);
}
