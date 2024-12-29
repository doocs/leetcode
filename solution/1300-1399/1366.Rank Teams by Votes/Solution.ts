function rankTeams(votes: string[]): string {
    const m = votes[0].length;
    const cnt: number[][] = Array.from({ length: 26 }, () => Array(m + 1).fill(0));
    for (const vote of votes) {
        for (let i = 0; i < m; i++) {
            cnt[vote.charCodeAt(i) - 65][i]++;
        }
    }
    const s: string[] = votes[0].split('');
    s.sort((a, b) => {
        const i = a.charCodeAt(0) - 65;
        const j = b.charCodeAt(0) - 65;
        for (let k = 0; k < m; k++) {
            if (cnt[i][k] !== cnt[j][k]) {
                return cnt[j][k] - cnt[i][k];
            }
        }
        return a.localeCompare(b);
    });
    return s.join('');
}
