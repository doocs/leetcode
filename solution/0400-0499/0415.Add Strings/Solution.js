/**
 * @param {string} num1
 * @param {string} num2
 * @return {string}
 */
var addStrings = function (num1, num2) {
    let ans = [];
    for (
        let i = num1.length - 1, j = num2.length - 1, sum = 0;
        i >= 0 || j >= 0 || sum > 0;
        i--, j--
    ) {
        const a = i >= 0 ? parseInt(num1.charAt(i), 10) : 0;
        const b = j >= 0 ? parseInt(num2.charAt(j), 10) : 0;
        sum += a + b;
        ans.unshift(sum % 10);
        sum = Math.floor(sum / 10);
    }
    return ans.join("");
};
