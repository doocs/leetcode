/**
 * @param {string} s
 * @param {number} numRows
 * @return {string}
 */
var convert = function (s, numRows) {
  if (numRows == 1) return s;
  let arr = new Array(numRows);
  for (let i = 0; i < numRows; i++) arr[i] = [];
  let index = 0,
    len = s.length,
    mi = 0,
    isDown = true;
  while (index < len) {
    arr[mi].push(s[index]);
    index++;

    if (mi >= numRows - 1) isDown = false;
    else if (mi <= 0) isDown = true;

    if (isDown) mi++;
    else mi--;
  }
  let ans = [];
  for (let item of arr) {
    ans = ans.concat(item);
  }
  return ans.join("");
};

const s = "AB",
  numRows = 1;

console.log(convert(s, numRows));
