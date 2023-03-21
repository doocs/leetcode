function leastMinutes(n: number): number {
    let ans = 1;
    for (let speed = 1; speed < n; speed <<= 1) {
        ++ans;
    }
    return ans;
}
