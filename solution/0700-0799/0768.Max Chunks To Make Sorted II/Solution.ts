function maxChunksToSorted(arr: number[]): number {
    let stack = []; // 左进左出
    for (let num of arr) {
        if (stack.length && num < stack[0]) {
            let max = stack.shift();
            while (stack.length && num < stack[0]) {
                stack.shift();
            }
            stack.unshift(max);
        } else {
            stack.unshift(num);
        }
    }
    return stack.length;
}
