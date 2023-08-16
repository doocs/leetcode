function checkIfCanBreak(s1: string, s2: string): boolean {
    const cs1: string[] = Array.from(s1);
    const cs2: string[] = Array.from(s2);
    cs1.sort();
    cs2.sort();
    const check = (cs1: string[], cs2: string[]) => {
        for (let i = 0; i < cs1.length; i++) {
            if (cs1[i] < cs2[i]) {
                return false;
            }
        }
        return true;
    };
    return check(cs1, cs2) || check(cs2, cs1);
}
