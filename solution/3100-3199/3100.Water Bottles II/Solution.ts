function maxBottlesDrunk(numBottles: number, numExchange: number): number {
    let ans = numBottles;
    while (numBottles >= numExchange) {
        numBottles -= numExchange;
        ++numExchange;
        ++ans;
        ++numBottles;
    }
    return ans;
}
