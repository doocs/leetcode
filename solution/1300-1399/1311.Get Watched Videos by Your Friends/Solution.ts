function watchedVideosByFriends(
    watchedVideos: string[][],
    friends: number[][],
    id: number,
    level: number,
): string[] {
    let q: number[] = [id];
    const n: number = friends.length;
    const vis: boolean[] = Array(n).fill(false);
    vis[id] = true;
    while (level-- > 0) {
        const nq: number[] = [];
        for (const i of q) {
            for (const j of friends[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    nq.push(j);
                }
            }
        }
        q = nq;
    }
    const cnt: { [key: string]: number } = {};
    for (const i of q) {
        for (const v of watchedVideos[i]) {
            cnt[v] = (cnt[v] || 0) + 1;
        }
    }
    const ans: string[] = Object.keys(cnt);
    ans.sort((a, b) => {
        if (cnt[a] === cnt[b]) {
            return a.localeCompare(b);
        }
        return cnt[a] - cnt[b];
    });
    return ans;
}
