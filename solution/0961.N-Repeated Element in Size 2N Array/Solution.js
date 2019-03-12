const repeatedNTimes = function (A) {
    let ss = new Set();
    for (let i = 0; i < A.length; i++) {
        if (ss.has(A[i])) {
            return A[i];
        }
        ss.add(A[i]);
    }
    return null;
};