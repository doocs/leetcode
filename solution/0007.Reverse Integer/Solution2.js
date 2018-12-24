/**
 * @param {number} x
 * @return {number}
 */
var reverse = function (x) {
    const s = x + ""
    let i = 0
    let sign = 1
    if (s[i] == "-") {
        i++
        sign = -1
    }
    if (s[i] == "+") {
        i++
    }
    let num = 0
    for (let j = s.length - 1; j >= i; j--) {
        num = num * 10 + parseInt(s[j])
    }
    num *= sign
    let max = 2
    for (let n = 0; n < 30; n++){
        max *= 2
    }
    if (num > max || num < -max){
        return 0
    }
    return num
};

console.log(reverse(1563847412))