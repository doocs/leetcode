function countSubstrings(s: string, c: string): number {
    const cnt = s.split('').filter(ch => ch === c).length;
    return cnt + Math.floor((cnt * (cnt - 1)) / 2);
}
