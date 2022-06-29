function strongPasswordCheckerII(password: string): boolean {
    if (password.length < 8) return false;
    if (!/[a-z]+/g.test(password)) return false;
    if (!/[A-Z]+/g.test(password)) return false;
    if (!/[0-9]+/g.test(password)) return false;
    if (!/[\!\@\#\$\%\^\&\*\(\)\-\+]+/g.test(password)) return false;
    const n = password.length;
    for (let i = 1; i < n; i++) {
        if (password.charAt(i) == password.charAt(i - 1)) return false;
    }
    return true;
}
