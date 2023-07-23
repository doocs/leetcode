function minimumPerimeter(neededApples: number): number {
    let l = 1;
    let r = 100000;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (2 * mid * (mid + 1) * (2 * mid + 1) >= neededApples) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return 8 * l;
}
