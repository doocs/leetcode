function cellsInRange(s: string): string[] {
    const ans: string[] = [];
    for (let i = s.charCodeAt(0); i <= s.charCodeAt(3); ++i) {
        for (let j = s.charCodeAt(1); j <= s.charCodeAt(4); ++j) {
            ans.push(String.fromCharCode(i) + String.fromCharCode(j));
        }
    }
    return ans;
}
