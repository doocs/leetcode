function getPermutation(n: number, k: number): string {
    let ans = '';
    const vis = Array.from({ length: n + 1 }, () => false);
    for (let i = 0; i < n; i++) {
        let fact = 1;
        for (let j = 1; j < n - i; j++) {
            fact *= j;
        }
        for (let j = 1; j <= n; j++) {
            if (!vis[j]) {
                if (k > fact) {
                    k -= fact;
                } else {
                    ans += j;
                    vis[j] = true;
                    break;
                }
            }
        }
    }
    return ans;
}
