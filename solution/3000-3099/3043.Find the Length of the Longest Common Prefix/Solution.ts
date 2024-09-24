function longestCommonPrefix(arr1: number[], arr2: number[]): number {
    const s: Set<number> = new Set<number>();
    for (let x of arr1) {
        for (; x; x = Math.floor(x / 10)) {
            s.add(x);
        }
    }
    let ans: number = 0;
    for (let x of arr2) {
        for (; x; x = Math.floor(x / 10)) {
            if (s.has(x)) {
                ans = Math.max(ans, Math.floor(Math.log10(x)) + 1);
            }
        }
    }
    return ans;
}
