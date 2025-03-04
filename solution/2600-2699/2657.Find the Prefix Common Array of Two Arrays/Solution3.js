function findThePrefixCommonArray(A, B) {
    const n = A.length;
    const res = Array(n).fill(0);
    const [setA, setB] = [new Set(), new Set()];

    for (let i = 0, c = 0; i < n; i++) {
        if (setA.has(B[i])) c++;
        if (setB.has(A[i])) c++;
        if (A[i] === B[i]) c++;

        setA.add(A[i]);
        setB.add(B[i]);
        res[i] = c;
    }

    return res;
}
