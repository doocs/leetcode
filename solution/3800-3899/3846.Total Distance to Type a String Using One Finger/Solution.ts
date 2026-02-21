const pos: Record<string, [number, number]> = {};

const keys = ['qwertyuiop', 'asdfghjkl', 'zxcvbnm'];
keys.forEach((row, i) => {
    [...row].forEach((key, j) => {
        pos[key] = [i, j];
    });
});

function totalDistance(s: string): number {
    let pre = 'a';
    let ans = 0;

    for (const cur of s) {
        const [x1, y1] = pos[pre];
        const [x2, y2] = pos[cur];
        ans += Math.abs(x1 - x2) + Math.abs(y1 - y2);
        pre = cur;
    }

    return ans;
}
