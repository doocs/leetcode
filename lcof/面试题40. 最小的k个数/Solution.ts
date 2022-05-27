function getLeastNumbers(arr: number[], k: number): number[] {
    let start = 0;
    let end = arr.length;
    while (start < end && end > k) {
        const index = start + Math.floor(Math.random() * (end - start));
        [arr[start], arr[index]] = [arr[index], arr[start]];
        const num = arr[start];
        let mark = start;
        for (let i = start + 1; i < end; i++) {
            if (arr[i] < num) {
                mark++;
                [arr[i], arr[mark]] = [arr[mark], arr[i]];
            }
        }
        [arr[start], arr[mark]] = [arr[mark], arr[start]];

        if (mark >= k) {
            end = mark;
        } else {
            start = mark + 1;
        }
    }
    return arr.slice(0, k);
}
