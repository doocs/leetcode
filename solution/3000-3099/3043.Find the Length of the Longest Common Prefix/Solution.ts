function longestCommonPrefix(arr1: number[], arr2: number[]): number {
    const s: Set<number> = new Set<number>();
    for (let x of arr1) {
        for (; x; x = (x / 10) | 0) {
            s.add(x % 10);
        }
    }
    let ans: number = 0;
    for (let x of arr2) {
        for (; x; x = (x / 10) | 0) {
            if (s.has(x % 10)) {
                ans = Math.max(ans, Math.floor(Math.log10(x)) + 1);
            }
        }
    }
    return ans;
}
