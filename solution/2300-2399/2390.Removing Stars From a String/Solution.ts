function removeStars(s: string): string {
    const ans: string[] = [];
    for (const c of s) {
        if (c === '*') {
            ans.pop();
        } else {
            ans.push(c);
        }
    }
    return ans.join('');
}
