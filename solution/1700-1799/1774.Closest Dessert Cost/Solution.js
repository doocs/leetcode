const closestCost = function (baseCosts, toppingCosts, target) {
    let closestDessertCost = -Infinity;
    function dfs(dessertCost, j) {
        const tarCurrDiff = Math.abs(target - dessertCost);
        const tarCloseDiff = Math.abs(target - closestDessertCost);
        if (tarCurrDiff < tarCloseDiff) {
            closestDessertCost = dessertCost;
        } else if (tarCurrDiff === tarCloseDiff && dessertCost < closestDessertCost) {
            closestDessertCost = dessertCost;
        }
        if (dessertCost > target) return;
        if (j === toppingCosts.length) return;
        for (let count = 0; count <= 2; count++) {
            dfs(dessertCost + count * toppingCosts[j], j + 1);
        }
    }
    for (let i = 0; i < baseCosts.length; i++) {
        dfs(baseCosts[i], 0);
    }
    return closestDessertCost;
};
