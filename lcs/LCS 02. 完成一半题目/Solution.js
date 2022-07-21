/**
 * @param {number[]} questions
 * @return {number}
 */
var halfQuestions = function (questions) {
    let counter = new Array(1010).fill(0);
    for (const q of questions) {
        ++counter[q];
    }
    counter.sort((a, b) => b - a);
    let ans = 0;
    let n = questions.length >> 1;
    for (let i = 0; n > 0; ++i) {
        ++ans;
        n -= counter[i];
    }
    return ans;
};
