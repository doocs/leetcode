const MX = 100001;

const primes: number[][] = Array.from({ length: MX }, () => []);

for (let i = 2; i < MX; i++) {
    if (primes[i].length === 0) {
        for (let j = i; j < MX; j += i) {
            primes[j].push(i);
        }
    }
}

function longestSubarray(nums: number[], k: number): number {
    const cnt = new Map<number, number>();

    let ans = 0;
    let l = 0;

    for (let r = 0; r < nums.length; r++) {
        for (const p of primes[nums[r]]) {
            cnt.set(p, (cnt.get(p) ?? 0) + 1);
        }

        while (cnt.size > k) {
            for (const p of primes[nums[l]]) {
                cnt.set(p, cnt.get(p)! - 1);

                if (cnt.get(p) === 0) {
                    cnt.delete(p);
                }
            }
            l++;
        }

        ans = Math.max(ans, r - l + 1);
    }

    return ans;
}
