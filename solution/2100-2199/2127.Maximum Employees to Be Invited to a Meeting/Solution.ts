function maximumInvitations(favorite: number[]): number {
    return Math.max(maxCycle(favorite), topologicalSort(favorite));
}

function maxCycle(fa: number[]): number {
    const n = fa.length;
    const vis: boolean[] = Array(n).fill(false);
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (vis[i]) {
            continue;
        }
        const cycle: number[] = [];
        let j = i;
        for (; !vis[j]; j = fa[j]) {
            cycle.push(j);
            vis[j] = true;
        }
        for (let k = 0; k < cycle.length; ++k) {
            if (cycle[k] === j) {
                ans = Math.max(ans, cycle.length - k);
            }
        }
    }
    return ans;
}

function topologicalSort(fa: number[]): number {
    const n = fa.length;
    const indeg: number[] = Array(n).fill(0);
    const dist: number[] = Array(n).fill(1);
    for (const v of fa) {
        ++indeg[v];
    }
    const q: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (indeg[i] === 0) {
            q.push(i);
        }
    }
    let ans = 0;
    while (q.length) {
        const i = q.pop()!;
        dist[fa[i]] = Math.max(dist[fa[i]], dist[i] + 1);
        if (--indeg[fa[i]] === 0) {
            q.push(fa[i]);
        }
    }
    for (let i = 0; i < n; ++i) {
        if (i === fa[fa[i]]) {
            ans += dist[i];
        }
    }
    return ans;
}
