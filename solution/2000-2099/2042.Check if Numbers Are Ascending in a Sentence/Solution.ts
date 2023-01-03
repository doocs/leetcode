function areNumbersAscending(s: string): boolean {
    let pre = -1;
    for (const cur of s.split(' ')) {
        if (cur[0] <= '9') {
            const num = Number(cur);
            if (num <= pre) {
                return false;
            }
            pre = num;
        }
    }
    return true;
}
