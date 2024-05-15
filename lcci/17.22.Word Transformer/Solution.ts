function findLadders(beginWord: string, endWord: string, wordList: string[]): string[] {
    const ans: string[] = [beginWord];
    const vis: boolean[] = Array(wordList.length).fill(false);
    const check = (s: string, t: string): boolean => {
        if (s.length !== t.length) {
            return false;
        }
        let cnt = 0;
        for (let i = 0; i < s.length; ++i) {
            if (s[i] !== t[i]) {
                ++cnt;
            }
        }
        return cnt === 1;
    };
    const dfs = (s: string): boolean => {
        if (s === endWord) {
            return true;
        }
        for (let i = 0; i < wordList.length; ++i) {
            const t: string = wordList[i];
            if (!vis[i] && check(s, t)) {
                vis[i] = true;
                ans.push(t);
                if (dfs(t)) {
                    return true;
                }
                ans.pop();
            }
        }
        return false;
    };
    return dfs(beginWord) ? ans : [];
}
