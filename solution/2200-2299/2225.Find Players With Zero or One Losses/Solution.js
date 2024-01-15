var findWinners = function (matches) {
    const onlyWins = new Set(),
        oneLose = new Set(),
        moreLosses = new Set();

    for (const [winner, loser] of matches) {
        if (!moreLosses.has(loser)) {
            if (oneLose.has(loser)) {
                oneLose.delete(loser);
                moreLosses.add(loser);
            } else {
                onlyWins.delete(loser);
                oneLose.add(loser);
            }
        }

        if (!moreLosses.has(winner) && !oneLose.has(winner)) {
            onlyWins.add(winner);
        }
    }

    return [[...onlyWins].sort((a, b) => a - b), [...oneLose].sort((a, b) => a - b)];
};
