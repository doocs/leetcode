/**
 * @param {number[]} questions
 * @return {number}
 */
var halfQuestions = function (questions) {
    const cnt = new Array(1010).fill(0);
    for (const x of questions) {
        ++cnt[x];
    }
    cnt.sort((a, b) => b - a);
    let ans = 0;
    let n = questions.length >> 1;
    for (let i = 0; n > 0; ++i) {
        ++ans;
        n -= cnt[i];
    }
    return ans;
};
