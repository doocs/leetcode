/**
 * @param {number[]} arr
 * @param {number[][]} queries
 * @return {number[]}
 */
 var xorQueries = function(arr, queries) {
    let n = arr.length;
    let xors = new Array(n + 1).fill(0);
    for (let i = 0; i < n; i++) {
        xors[i + 1] = xors[i] ^ arr[i]; 
    }
    let res = [];
    for (let querie of queries) {
        let [start, end] = querie;
        res.push(xors[start] ^ xors[end + 1]);
    }
    return res;
};