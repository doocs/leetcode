/**
 * Definition for a category handler.
 * class CategoryHandler {
 *     constructor(categories: number[]);
 *     public haveSameCategory(a: number, b: number): boolean;
 * }
 */
function numberOfCategories(n: number, categoryHandler: CategoryHandler): number {
    const p: number[] = new Array(n).fill(0).map((_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (let a = 0; a < n; ++a) {
        for (let b = a + 1; b < n; ++b) {
            if (categoryHandler.haveSameCategory(a, b)) {
                p[find(a)] = find(b);
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (i === p[i]) {
            ++ans;
        }
    }
    return ans;
}
