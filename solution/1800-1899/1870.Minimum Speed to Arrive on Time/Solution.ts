function minSpeedOnTime(dist: number[], hour: number): number {
    if (dist.length > Math.ceil(hour)) return -1;

    const check = (speed: number) => {
        const n = dist.length;
        let time = 0;

        for (let i = 0; i < n; i++) {
            const t = dist[i] / speed;
            time += i === n - 1 ? t : Math.ceil(t);
        }

        return hour >= time;
    };

    const max = 10 ** 7;
    let [l, r] = [1, max];

    while (l <= r) {
        const i = (l + r) >> 1;
        if (check(i)) r = i - 1;
        else l = i + 1;
    }

    return l <= max ? l : -1;
}
