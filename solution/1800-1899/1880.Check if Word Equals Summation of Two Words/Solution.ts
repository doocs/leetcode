function isSumEqual(firstWord: string, secondWord: string, targetWord: string): boolean {
    const f = (s: string): number => {
        let ans = 0;
        for (const c of s) {
            ans = ans * 10 + c.charCodeAt(0) - 97;
        }
        return ans;
    };
    return f(firstWord) + f(secondWord) == f(targetWord);
}
