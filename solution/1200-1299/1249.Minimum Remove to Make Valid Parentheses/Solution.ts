function minRemoveToMakeValid(s: string): string {
    let left = 0;
    let right = 0;
    for (const c of s) {
        if (c === '(') {
            left++;
        } else if (c === ')') {
            if (right < left) {
                right++;
            }
        }
    }

    let hasLeft = 0;
    let res = '';
    for (const c of s) {
        if (c === '(') {
            if (hasLeft < right) {
                hasLeft++;
                res += c;
            }
        } else if (c === ')') {
            if (hasLeft != 0 && right !== 0) {
                right--;
                hasLeft--;
                res += c;
            }
        } else {
            res += c;
        }
    }
    return res;
}
