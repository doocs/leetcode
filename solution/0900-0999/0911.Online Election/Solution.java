class TopVotedCandidate {
    private int[] times;
    private int[] wins;

    public TopVotedCandidate(int[] persons, int[] times) {
        int n = persons.length;
        int mx = 0, cur = 0;
        this.times = times;
        wins = new int[n];
        int[] counter = new int[n];
        for (int i = 0; i < n; ++i) {
            int p = persons[i];
            if (++counter[p] >= mx) {
                mx = counter[p];
                cur = p;
            }
            wins[i] = cur;
        }
    }

    public int q(int t) {
        int left = 0, right = wins.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (times[mid] <= t) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return wins[left];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */