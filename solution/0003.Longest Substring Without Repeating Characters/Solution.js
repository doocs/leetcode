/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function(s) {
    var start = 0; // 非重复字符串开始索引
    var max = 0; // 最长字符串长度
    var visitedCharByPosition = {};
    for (var position = 0; position < s.length; position++) {
        var nextChar = s[position];
        if (nextChar in visitedCharByPosition && visitedCharByPosition[nextChar] >= start) {
            // 有重复，非重复字符串索引从下一个 index 开始
            start = visitedCharByPosition[nextChar] + 1;
            visitedCharByPosition[nextChar] = position;
        } else {
            visitedCharByPosition[nextChar] = position;
            // 非重复，求非重复值
            max = Math.max(max, position + 1 - start);
        }
    }
    return max;
};