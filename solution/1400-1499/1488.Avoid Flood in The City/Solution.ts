import { AvlTree } from 'datastructures-js';

function avoidFlood(rains: number[]): number[] {
    const n = rains.length;
    const ans = Array(n).fill(-1);
    const sunny = new AvlTree<number>((a, b) => a - b);
    const rainy = new Map<number, number>();

    for (let i = 0; i < n; ++i) {
        const v = rains[i];
        if (v > 0) {
            if (rainy.has(v)) {
                const last = rainy.get(v)!;
                const node = sunny.ceil(last + 1);
                if (!node) return [];
                const t = node.getValue();
                ans[t] = v;
                sunny.remove(t);
            }
            rainy.set(v, i);
        } else {
            sunny.insert(i);
            ans[i] = 1;
        }
    }
    return ans;
}
