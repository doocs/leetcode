function restoreString(s: string, indices: number[]): string {
    const ans: string[] = [];
    for (let i = 0; i < s.length; i++) {
        ans[indices[i]] = s[i];
    }
    return ans.join('');
}
