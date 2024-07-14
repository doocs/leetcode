function isLongPressedName(name: string, typed: string): boolean {
    const [m, n] = [name.length, typed.length];
    let i = 0;
    let j = 0;
    while (i < m && j < n) {
        if (name[i] !== typed[j]) {
            return false;
        }
        let x = i + 1;
        while (x < m && name[x] === name[i]) {
            x++;
        }
        let y = j + 1;
        while (y < n && typed[y] === typed[j]) {
            y++;
        }
        if (x - i > y - j) {
            return false;
        }
        i = x;
        j = y;
    }
    return i === m && j === n;
}
