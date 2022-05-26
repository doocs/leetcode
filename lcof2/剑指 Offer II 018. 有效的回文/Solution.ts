function isPalindrome(s: string): boolean {
    const str = s.replace(/[^a-zA-Z0-9]/g, '');
    let l = 0;
    let r = str.length - 1;
    while (l < r) {
        if (str[l].toLocaleLowerCase() !== str[r].toLocaleLowerCase()) {
            return false;
        }
        l++;
        r--;
    }
    return true;
}
