class TopVotedCandidate {
    private int[] times;
    private int[] wins;

    public TopVotedCandidate(int[] persons, int[] times) {
        int n = persons.length;
        wins = new int[n];
        this.times = times;
        int[] cnt = new int[n];
        int cur = 0;
        for (int i = 0; i < n; ++i) {
            int p = persons[i];
            ++cnt[p];
            if (cnt[cur] <= cnt[p]) {
                cur = p;
            }
            wins[i] = cur;
        }
    }
    
    public int q(int t) {
        int i = Arrays.binarySearch(times, t + 1);
        i = i < 0 ? -i - 2 : i - 1;
        return wins[i];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */