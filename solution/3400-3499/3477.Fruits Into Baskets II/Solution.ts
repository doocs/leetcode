function numOfUnplacedFruits(fruits: number[], baskets: number[]): number {
    const n = fruits.length;
    const vis: boolean[] = Array(n).fill(false);
    let ans = n;
    for (const x of fruits) {
        for (let i = 0; i < n; ++i) {
            if (baskets[i] >= x && !vis[i]) {
                vis[i] = true;
                --ans;
                break;
            }
        }
    }
    return ans;
}
