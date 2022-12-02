function minOperations(boxes: string): number[] {
    const n = boxes.length;
    const ans = new Array(n).fill(0);
    for (let i = 1, count = 0; i < n; i++) {
        if (boxes[i - 1] === '1') {
            count++;
        }
        ans[i] = ans[i - 1] + count;
    }
    for (let i = n - 2, count = 0, sum = 0; i >= 0; i--) {
        if (boxes[i + 1] === '1') {
            count++;
        }
        sum += count;
        ans[i] += sum;
    }
    return ans;
}
