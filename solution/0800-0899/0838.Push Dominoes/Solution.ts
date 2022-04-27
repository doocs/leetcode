function pushDominoes(dominoes: string): string {
    const n = dominoes.length;
    const map = {
        L: -1,
        R: 1,
        '.': 0,
    };
    let ans = new Array(n).fill(0);
    let visited = new Array(n).fill(0);
    let queue = [];
    let depth = 1;
    for (let i = 0; i < n; i++) {
        let cur = map[dominoes.charAt(i)];
        if (cur) {
            queue.push(i);
            visited[i] = depth;
            ans[i] = cur;
        }
    }
    while (queue.length) {
        depth++;
        let nextLevel = [];
        for (let i of queue) {
            const dx = ans[i];
            let x = i + dx;
            if (x >= 0 && x < n && [0, depth].includes(visited[x])) {
                ans[x] += dx;
                visited[x] = depth;
                nextLevel.push(x);
            }
        }
        queue = nextLevel;
    }
    return ans
        .map(d => {
            if (!d) return '.';
            else if (d < 0) return 'L';
            else return 'R';
        })
        .join('');
}
