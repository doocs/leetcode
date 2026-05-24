function passwordStrength(password: string): number {
    const st = new Set(password);

    let ans = 0;

    for (const ch of st) {
        if (/[a-z]/u.test(ch)) {
            ans += 1;
        } else if (/[A-Z]/u.test(ch)) {
            ans += 2;
        } else if (/\d/u.test(ch)) {
            ans += 3;
        } else {
            ans += 5;
        }
    }

    return ans;
}
