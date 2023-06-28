function circularGameLosers(n: number, k: number): number[] {
    const vis = new Array(n).fill(false);
    const ans: number[] = [];
    for (let i = 0, p = 1; !vis[i]; p++) {
        vis[i] = true;
        i = (i + p * k) % n;
    }
    for (let i = 0; i < vis.length; i++) {
        if (!vis[i]) {
            ans.push(i + 1);
        }
    }
    return ans;
}
