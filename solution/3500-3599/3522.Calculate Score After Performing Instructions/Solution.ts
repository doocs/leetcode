function calculateScore(instructions: string[], values: number[]): number {
    const n = values.length;
    const vis: boolean[] = Array(n).fill(false);
    let ans = 0;
    let i = 0;

    while (i >= 0 && i < n && !vis[i]) {
        vis[i] = true;
        if (instructions[i][0] === 'a') {
            ans += values[i];
            i += 1;
        } else {
            i += values[i];
        }
    }

    return ans;
}
