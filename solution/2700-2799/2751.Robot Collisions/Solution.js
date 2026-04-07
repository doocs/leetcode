/**
 * @param {number[]} positions
 * @param {number[]} healths
 * @param {string} directions
 * @return {number[]}
 */
var survivedRobotsHealths = function (positions, healths, directions) {
    const n = positions.length;
    const idx = Array.from({ length: n }, (_, i) => i).sort((a, b) => positions[a] - positions[b]);

    const stk = [];

    for (const i of idx) {
        if (directions[i] === 'R') {
            stk.push(i);
            continue;
        }

        while (stk.length && healths[i] > 0) {
            const j = stk[stk.length - 1];

            if (healths[j] > healths[i]) {
                healths[j]--;
                healths[i] = 0;
            } else if (healths[j] < healths[i]) {
                healths[i]--;
                healths[j] = 0;
                stk.pop();
            } else {
                healths[i] = healths[j] = 0;
                stk.pop();
                break;
            }
        }
    }

    return healths.filter(h => h > 0);
};
