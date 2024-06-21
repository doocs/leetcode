function temperatureTrend(temperatureA: number[], temperatureB: number[]): number {
    let [ans, f] = [0, 0];
    for (let i = 0; i < temperatureA.length - 1; ++i) {
        let x = temperatureA[i + 1] - temperatureA[i];
        let y = temperatureB[i + 1] - temperatureB[i];
        if ((x === 0 && y === 0) || x * y > 0) {
            ans = Math.max(ans, ++f);
        } else {
            f = 0;
        }
    }
    return ans;
}
