/**
 * @param {number[]} citations
 * @return {number}
 */
var hIndex = function(citations) {
    citations.sort((a, b) => b - a);
    for (let h = citations.length; h; --h) {
        if (citations[h - 1] >= h) {
            return h;
        }
    }
    return 0;
};
