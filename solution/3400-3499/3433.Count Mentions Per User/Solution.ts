function countMentions(numberOfUsers: number, events: string[][]): number[] {
    events.sort((a, b) => {
        const x = +a[1];
        const y = +b[1];
        if (x === y) {
            return a[0].charAt(2) < b[0].charAt(2) ? -1 : 1;
        }
        return x - y;
    });

    const ans: number[] = Array(numberOfUsers).fill(0);
    const onlineT: number[] = Array(numberOfUsers).fill(0);
    let lazy = 0;

    for (const [etype, ts, s] of events) {
        const cur = +ts;
        if (etype.charAt(0) === 'O') {
            const userID = +s;
            onlineT[userID] = cur + 60;
        } else if (s.charAt(0) === 'A') {
            lazy++;
        } else if (s.charAt(0) === 'H') {
            for (let i = 0; i < numberOfUsers; i++) {
                if (onlineT[i] <= cur) {
                    ans[i]++;
                }
            }
        } else {
            const mentions = s.split(' ');
            for (const m of mentions) {
                const userID = +m.slice(2);
                ans[userID]++;
            }
        }
    }

    if (lazy > 0) {
        for (let i = 0; i < numberOfUsers; i++) {
            ans[i] += lazy;
        }
    }

    return ans;
}
