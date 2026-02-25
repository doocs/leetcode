function numSteps(s: string): number {
    let ans = 0;
    let carry = false;

    for (let i = s.length - 1; i > 0; i--) {
        let c = s[i];

        if (carry) {
            if (c === '0') {
                c = '1';
                carry = false;
            } else {
                c = '0';
            }
        }

        if (c === '1') {
            ans++;
            carry = true;
        }

        ans++;
    }

    if (carry) {
        ans++;
    }

    return ans;
}
