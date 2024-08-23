/**
 * @param {string} expression
 * @return {string}
 */
var fractionAddition = function (expression) {
    let x = 0,
        y = 1;

    if (!expression.startsWith('-') && !expression.startsWith('+')) {
        expression = '+' + expression;
    }

    let i = 0;
    const n = expression.length;

    while (i < n) {
        const sign = expression[i] === '-' ? -1 : 1;
        i++;

        let j = i;
        while (j < n && expression[j] !== '+' && expression[j] !== '-') {
            j++;
        }

        const [a, b] = expression.slice(i, j).split('/').map(Number);
        x = x * b + sign * a * y;
        y *= b;
        i = j;
    }

    const gcd = (a, b) => {
        while (b !== 0) {
            [a, b] = [b, a % b];
        }
        return Math.abs(a);
    };

    const z = gcd(x, y);
    x = Math.floor(x / z);
    y = Math.floor(y / z);

    return `${x}/${y}`;
};
