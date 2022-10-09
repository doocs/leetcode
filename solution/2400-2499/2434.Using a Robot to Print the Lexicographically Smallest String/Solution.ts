function robotWithString(s: string): string {
    let cnt = new Array(128).fill(0);
    for (let c of s) cnt[c.charCodeAt(0)] += 1;
    let min_index = 'a'.charCodeAt(0);
    let ans = [];
    let stack = [];
    for (let c of s) {
        cnt[c.charCodeAt(0)] -= 1;
        while (min_index <= 'z'.charCodeAt(0) && cnt[min_index] == 0) {
            min_index += 1;
        }
        stack.push(c);
        while (
            stack.length > 0 &&
            stack[stack.length - 1].charCodeAt(0) <= min_index
        ) {
            ans.push(stack.pop());
        }
    }
    return ans.join('');
}
