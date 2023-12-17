const ps = Array(2e5).fill(0);

const init = (() => {
    for (let i = 1; i <= 1e5; ++i) {
        const s: string = i.toString();
        const t1: string = s.split('').reverse().join('');
        const t2: string = s.slice(0, -1).split('').reverse().join('');
        ps[2 * i - 2] = parseInt(s + t1, 10);
        ps[2 * i - 1] = parseInt(s + t2, 10);
    }
    ps.sort((a, b) => a - b);
})();

function minimumCost(nums: number[]): number {
    const search = (x: number): number => {
        let [l, r] = [0, ps.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (ps[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const f = (x: number): number => {
        return nums.reduce((acc, v) => acc + Math.abs(v - x), 0);
    };

    nums.sort((a, b) => a - b);
    const i: number = search(nums[nums.length >> 1]);
    let ans: number = Number.MAX_SAFE_INTEGER;
    for (let j = i - 1; j <= i + 1; j++) {
        if (j >= 0 && j < ps.length) {
            ans = Math.min(ans, f(ps[j]));
        }
    }
    return ans;
}
