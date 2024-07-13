function minOperations(logs: string[]): number {
    let ans = 0;
    for (const x of logs) {
        if (x === '../') {
            ans && ans--;
        } else if (x !== './') {
            ans++;
        }
    }
    return ans;
}
