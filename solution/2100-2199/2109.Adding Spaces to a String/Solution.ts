function addSpaces(s: string, spaces: number[]): string {
    const ans: string[] = [];
    for (let i = 0, j = 0; i < s.length; i++) {
        if (i === spaces[j]) {
            ans.push(' ');
            j++;
        }
        ans.push(s[i]);
    }
    return ans.join('');
}
