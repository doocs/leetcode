function maxChunksToSorted(arr: number[]): number {
    const stack = [];
    for (const num of arr) {
        if (stack.length !== 0 && num < stack[stack.length - 1]) {
            const max = stack.pop();
            while (stack.length !== 0 && num < stack[stack.length - 1]) {
                stack.pop();
            }
            stack.push(max);
        } else {
            stack.push(num);
        }
    }
    return stack.length;
}
