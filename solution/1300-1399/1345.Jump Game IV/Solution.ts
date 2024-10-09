function minJumps(arr: number[]): number {
    const g: Map<number, number[]> = new Map();
    const n = arr.length;
    for (let i = 0; i < n; ++i) {
        if (!g.has(arr[i])) {
            g.set(arr[i], []);
        }
        g.get(arr[i])!.push(i);
    }
    let q: number[] = [0];
    const vis: boolean[] = Array(n).fill(false);
    vis[0] = true;
    for (let ans = 0; ; ++ans) {
        const nq: number[] = [];
        for (const i of q) {
            if (i === n - 1) {
                return ans;
            }
            for (const j of g.get(arr[i])!) {
                if (!vis[j]) {
                    vis[j] = true;
                    nq.push(j);
                }
            }
            g.get(arr[i])!.length = 0;
            for (const j of [i - 1, i + 1]) {
                if (j >= 0 && j < n && !vis[j]) {
                    vis[j] = true;
                    nq.push(j);
                }
            }
        }
        q = nq;
    }
}
