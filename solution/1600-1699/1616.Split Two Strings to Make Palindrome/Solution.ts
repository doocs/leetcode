function checkPalindromeFormation(a: string, b: string): boolean {
    const check1 = (a: string, b: string) => {
        let i = 0;
        let j = b.length - 1;
        while (i < j && a.charAt(i) === b.charAt(j)) {
            i++;
            j--;
        }
        return i >= j || check2(a, i, j) || check2(b, i, j);
    };

    const check2 = (a: string, i: number, j: number) => {
        while (i < j && a.charAt(i) === a.charAt(j)) {
            i++;
            j--;
        }
        return i >= j;
    };
    return check1(a, b) || check1(b, a);
}
