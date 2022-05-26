/**
 * @param {string} solution
 * @param {string} guess
 * @return {number[]}
 */
var masterMind = function (solution, guess) {
    let counts1 = { R: 0, G: 0, B: 0, Y: 0 };
    let counts2 = { R: 0, G: 0, B: 0, Y: 0 };
    let res1 = 0;
    for (let i = 0; i < solution.length; i++) {
        let s1 = solution.charAt(i),
            s2 = guess.charAt(i);
        if (s1 == s2) {
            res1++;
        } else {
            counts1[s1] += 1;
            counts2[s2] += 1;
        }
    }
    let res2 = ['R', 'G', 'B', 'Y'].reduce(
        (a, c) => a + Math.min(counts1[c], counts2[c]),
        0,
    );
    return [res1, res2];
};
