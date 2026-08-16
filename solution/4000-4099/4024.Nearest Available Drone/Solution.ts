function nearestDrone(drones: number[][], target: number[]): number {
    let ans = -1;
    let mn = Infinity;
    const [tx, ty] = target;

    for (let i = 0; i < drones.length; i++) {
        const [x, y, r] = drones[i];

        const d = Math.abs(x - tx) + Math.abs(y - ty);

        if (d <= r && mn > d) {
            ans = i;
            mn = d;
        }
    }

    return ans;
}
