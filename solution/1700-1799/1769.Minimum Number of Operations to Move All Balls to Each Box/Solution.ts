function minOperations(boxes: string): number[] {
    const n = boxes.length;
    const left = new Array(n).fill(0);
    const right = new Array(n).fill(0);
    for (let i = 1, count = 0; i < n; i++) {
        if (boxes[i - 1] == '1') {
            count++;
        }
        left[i] = left[i - 1] + count;
    }
    for (let i = n - 2, count = 0; i >= 0; i--) {
        if (boxes[i + 1] == '1') {
            count++;
        }
        right[i] = right[i + 1] + count;
    }
    return left.map((v, i) => v + right[i]);
}
