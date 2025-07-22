function countCollisions(directions: string): number {
    const n = directions.length;
    let [l, r] = [0, n - 1];
    while (l < n && directions[l] == 'L') {
        ++l;
    }
    while (r >= 0 && directions[r] == 'R') {
        --r;
    }
    let ans = r - l + 1;
    for (let i = l; i <= r; ++i) {
        if (directions[i] === 'S') {
            --ans;
        }
    }
    return ans;
}
