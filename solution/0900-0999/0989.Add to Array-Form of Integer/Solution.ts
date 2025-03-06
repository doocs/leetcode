function addToArrayForm(num: number[], k: number): number[] {
    const ans: number[] = [];
    for (let i = num.length - 1; i >= 0 || k > 0; --i) {
        k += i >= 0 ? num[i] : 0;
        ans.push(k % 10);
        k = Math.floor(k / 10);
    }
    return ans.reverse();
}
