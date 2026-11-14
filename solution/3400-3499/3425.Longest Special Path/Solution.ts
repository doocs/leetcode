function longestSpecialPath(edges: number[][], nums: number[]): number[] {
    const n = nums.length;

    const graph: [number, number][][] = Array.from(
        { length: n },
        () => []
    );

    for (const [u, v, w] of edges) {
        graph[u].push([v, w]);
        graph[v].push([u, w]);
    }

    const last = new Map<number, number>();
    const prefix = new Array<number>(n + 1).fill(0);

    type Frame = {
        node: number;
        parent: number;
        left: number;
        depth: number;
        weight: number;
        old: number;
        exit: boolean;
    };

    const stack: Frame[] = [{
        node: 0,
        parent: -1,
        left: 0,
        depth: 0,
        weight: 0,
        old: 0,
        exit: false
    }];

    let bestLength = 0;
    let bestNodes = 1;

    while (stack.length > 0) {
        const f = stack.pop()!;
        const u = f.node;

        if (f.exit) {
            if (f.old === 0) {
                last.delete(nums[u]);
            } else {
                last.set(nums[u], f.old);
            }
            continue;
        }

        prefix[f.depth + 1] = prefix[f.depth] + f.weight;

        const old = last.get(nums[u]) ?? 0;
        const left = Math.max(f.left, old);

        last.set(nums[u], f.depth + 1);

        const length = prefix[f.depth + 1] - prefix[left + 1];
        const nodes = f.depth + 1 - left;

        if (
            length > bestLength ||
            (length === bestLength && nodes < bestNodes)
        ) {
            bestLength = length;
            bestNodes = nodes;
        }

        stack.push({
            node: u,
            parent: f.parent,
            left,
            depth: f.depth,
            weight: 0,
            old,
            exit: true
        });

        for (let i = graph[u].length - 1; i >= 0; i--) {
            const [v, w] = graph[u][i];

            if (v !== f.parent) {
                stack.push({
                    node: v,
                    parent: u,
                    left,
                    depth: f.depth + 1,
                    weight: w,
                    old: 0,
                    exit: false
                });
            }
        }
    }

    return [bestLength, bestNodes];
}
