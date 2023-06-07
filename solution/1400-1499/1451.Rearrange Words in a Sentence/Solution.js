/**
 * @param {string} text
 * @return {string}
 */
var arrangeWords = function (text) {
    let arr = text.split(' ');
    arr[0] = arr[0].toLocaleLowerCase();
    arr.sort((a, b) => a.length - b.length);
    arr[0] = arr[0][0].toLocaleUpperCase() + arr[0].substr(1);
    return arr.join(' ');
};
