function dividePlayers(skill) {
    let [sum, res, map] = [0, 0, new Map()];

    for (const x of skill) {
        sum += x;
        map.set(x, (map.get(x) || 0) + 1);
    }
    sum /= skill.length / 2;

    for (let [x, c] of map) {
        const complement = sum - x;
        if ((map.get(complement) ?? 0) !== c) return -1;
        if (x === complement) c /= 2;

        res += x * complement * c;
        map.delete(x);
        map.delete(complement);
    }

    return res;
}
