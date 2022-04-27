function reverseOnlyLetters(s: string): string {
    const n = s.length;
    let i = 0,
        j = n - 1;
    let ans = [...s];
    while (i < j) {
        while (!/[a-zA-Z]/.test(ans[i]) && i < j) i++;
        while (!/[a-zA-Z]/.test(ans[j]) && i < j) j--;
        [ans[i], ans[j]] = [ans[j], ans[i]];
        i++;
        j--;
    }
    return ans.join('');
}
