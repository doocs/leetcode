var distanceTraveled = function (mainTank, additionalTank) {
    let ans = 0,
        cur = 0;
    while (mainTank) {
        cur++;
        ans += 10;
        mainTank--;
        if (cur % 5 === 0 && additionalTank) {
            additionalTank--;
            mainTank++;
        }
    }
    return ans;
};
