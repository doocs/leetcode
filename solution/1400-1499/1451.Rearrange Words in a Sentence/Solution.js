/**
 * @param {string} text
 * @return {string}
 */
var arrangeWords = function (text) {
    text = text[0].toLocaleLowerCase() + text.substr(1);
    let arr = text.split(" ");
    arr.sort((a, b) => a.length - b.length);
    let b = arr[0][0].toLocaleUpperCase() + arr[0].substr(1);
    return arr.join(" ").replace(arr[0], b);
};
