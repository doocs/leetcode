/**
 * @param {string} firstWord
 * @param {string} secondWord
 * @param {string} targetWord
 * @return {boolean}
 */
 var isSumEqual = function(firstWord, secondWord, targetWord) {
    let carry = 0;
    let n1 = firstWord.length, n2 = secondWord.length;
    let n3 = targetWord.length;
    for (let i = 0; i < n3; i++) {
        let num1 = getNum(firstWord.charAt(n1 - 1 - i));
        let num2 = getNum(secondWord.charAt(n2 - 1 - i));
        let sum = carry + num1 + num2;
        if (getNum(targetWord.charAt(n3 - 1 - i)) != (sum % 10)) return false;
        carry = parseInt(sum / 10);
    }
    return true;
};

function getNum (char) {
    if (!char) return 0;
    return char.charCodeAt() - 'a'.charCodeAt();
}