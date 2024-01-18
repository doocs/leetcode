class TopVotedCandidate {
    private int[] times;
    private int[] winPersons;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        int mx = -1, curWin = -1;
        int n = persons.length;
        int[] counter = new int[n + 1];
        winPersons = new int[n];
        for (int i = 0; i < n; ++i) {
            if (++counter[persons[i]] >= mx) {
                mx = counter[persons[i]];
                curWin = persons[i];
            }
            winPersons[i] = curWin;
        }
    }

    public int q(int t) {
        int left = 0, right = winPersons.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (times[mid] <= t) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return winPersons[left];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */