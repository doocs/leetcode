function matchPlayersAndTrainers(players: number[], trainers: number[]): number {
    players.sort((a, b) => a - b);
    trainers.sort((a, b) => a - b);
    let j = 0;
    const m = players.length;
    for (const t of trainers) {
        if (j < m && players[j] <= t) {
            ++j;
        }
    }
    return j;
}
