function diStringMatch(s: string): number[] {
    const ans: number[] = [];
    let [low, high] = [0, s.length];
    for (const c of s) {
        if (c === 'I') {
            ans.push(low++);
        } else {
            ans.push(high--);
        }
    }
    ans.push(low);
    return ans;
}
