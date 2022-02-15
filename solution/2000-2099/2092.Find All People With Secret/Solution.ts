function findAllPeople(
    n: number,
    meetings: number[][],
    firstPerson: number,
): number[] {
    let parent: Array<number> = Array.from({ length: n + 1 }, (v, i) => i);
    parent[firstPerson] = 0;

    function findParent(index: number): number {
        if (parent[index] != index) parent[index] = findParent(parent[index]);
        return parent[index];
    }

    let map = new Map<number, Array<Array<number>>>();
    for (let meeting of meetings) {
        const time = meeting[2];
        let members: Array<Array<number>> = map.get(time) || new Array();
        members.push(meeting);
        map.set(time, members);
    }
    const times = [...map.keys()].sort((a, b) => a - b);
    for (let time of times) {
        // round 1
        for (let meeting of map.get(time)) {
            let [a, b] = meeting;
            if (!parent[findParent(a)] || !parent[findParent(b)]) {
                parent[findParent(a)] = 0;
                parent[findParent(b)] = 0;
            }
            parent[findParent(a)] = parent[findParent(b)];
        }
        // round 2
        for (let meeting of map.get(time)) {
            let [a, b] = meeting;
            if (!parent[findParent(a)] || !parent[findParent(b)]) {
                parent[findParent(a)] = 0;
                parent[findParent(b)] = 0;
            } else {
                parent[a] = a;
                parent[b] = b;
            }
        }
    }

    let ans = new Array<number>();
    for (let i = 0; i <= n; i++) {
        if (!parent[findParent(i)]) {
            ans.push(i);
        }
    }
    return ans;
}
