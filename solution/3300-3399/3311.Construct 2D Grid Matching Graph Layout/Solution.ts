function constructGridLayout(n: number, edges: number[][]): number[][] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }

    const deg: number[] = Array(5).fill(-1);
    for (let x = 0; x < n; x++) {
        deg[g[x].length] = x;
    }

    let row: number[] = [];
    if (deg[1] !== -1) {
        row.push(deg[1]);
    } else if (deg[4] === -1) {
        let x = deg[2];
        for (const y of g[x]) {
            if (g[y].length === 2) {
                row.push(x, y);
                break;
            }
        }
    } else {
        let x = deg[2];
        row.push(x);
        let pre = x;
        x = g[x][0];
        while (g[x].length > 2) {
            row.push(x);
            for (const y of g[x]) {
                if (y !== pre && g[y].length < 4) {
                    pre = x;
                    x = y;
                    break;
                }
            }
        }
        row.push(x);
    }

    const ans: number[][] = [row];
    const vis: boolean[] = Array(n).fill(false);
    const rowSize = row.length;

    for (let i = 0; i < Math.floor(n / rowSize) - 1; i++) {
        for (const x of row) {
            vis[x] = true;
        }
        const nxt: number[] = [];
        for (const x of row) {
            for (const y of g[x]) {
                if (!vis[y]) {
                    nxt.push(y);
                    break;
                }
            }
        }
        ans.push(nxt);
        row = nxt;
    }

    return ans;
}
