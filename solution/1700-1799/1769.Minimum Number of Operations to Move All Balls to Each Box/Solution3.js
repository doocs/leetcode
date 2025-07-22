function minOperations(boxes) {
    const n = boxes.length;
    const ans = Array(n).fill(0);
    const ones = [];

    for (let i = 0; i < n; i++) {
        if (+boxes[i]) {
            ones.push(i);
        }
    }

    for (let i = 0; i < n; i++) {
        for (const j of ones) {
            ans[i] += Math.abs(i - j);
        }
    }

    return ans;
}
