function minMutation(start: string, end: string, bank: string[]): number {
    const queue = [start];
    let res = 0;
    while (queue.length !== 0) {
        const n = queue.length;
        for (let i = 0; i < n; i++) {
            const s1 = queue.shift();
            if (s1 === end) {
                return res;
            }

            for (let j = bank.length - 1; j >= 0; j--) {
                const s2 = bank[j];
                let count = 0;
                for (let k = 0; k < 8; k++) {
                    if (s1[k] !== s2[k]) {
                        count++;
                    }
                }
                if (count <= 1) {
                    queue.push(...bank.splice(j, 1));
                }
            }
        }
        res++;
    }
    return -1;
}
