function addSpaces(s: string, spaces: number[]): string {
    let ans = '';
    for (let i = 0, j = 0; i < s.length; i++) {
        if (j < spaces.length && i === spaces[j]) {
            ans += ' ';
            ++j;
        }
        ans += s[i];
    }
    return ans;
}
