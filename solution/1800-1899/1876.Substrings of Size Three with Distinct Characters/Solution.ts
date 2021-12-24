function countGoodSubstrings(s: string): number {
    const n: number = s.length;
    let count: number = 0;
    for (let i: number = 0; i < n - 2; ++i) {
        let a: string = s.charAt(i),
            b: string = s.charAt(i + 1),
            c: string = s.charAt(i + 2);
        if (a != b && a != c && b != c) {
            ++count;
        }
    }
    return count;
}
