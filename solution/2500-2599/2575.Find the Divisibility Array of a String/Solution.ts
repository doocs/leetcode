function divisibilityArray(word: string, m: number): number[] {
    const ans: number[] = [];
    let x = 0;
    for (const c of word) {
        x = (x * 10 + Number(c)) % m;
        ans.push(x === 0 ? 1 : 0);
    }
    return ans;
}
