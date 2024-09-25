function mostVisited(n: number, rounds: number[]): number[] {
    const ans: number[] = [];
    const m = rounds.length - 1;
    if (rounds[0] <= rounds[m]) {
        for (let i = rounds[0]; i <= rounds[m]; ++i) {
            ans.push(i);
        }
    } else {
        for (let i = 1; i <= rounds[m]; ++i) {
            ans.push(i);
        }
        for (let i = rounds[0]; i <= n; ++i) {
            ans.push(i);
        }
    }
    return ans;
}
