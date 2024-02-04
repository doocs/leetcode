function generatePossibleNextMoves(currentState: string): string[] {
    const s = currentState.split('');
    const ans: string[] = [];
    for (let i = 0; i < s.length - 1; ++i) {
        if (s[i] === '+' && s[i + 1] === '+') {
            s[i] = s[i + 1] = '-';
            ans.push(s.join(''));
            s[i] = s[i + 1] = '+';
        }
    }
    return ans;
}
