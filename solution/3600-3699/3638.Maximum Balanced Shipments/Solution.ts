function maxBalancedShipments(weight: number[]): number {
    let [ans, mx] = [0, 0];
    for (const x of weight) {
        mx = Math.max(mx, x);
        if (x < mx) {
            ans++;
            mx = 0;
        }
    }
    return ans;
}
