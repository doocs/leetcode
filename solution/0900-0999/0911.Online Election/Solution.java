class TopVotedCandidate {
    int[] persons;
    int[] times;
    int[] winPerson;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.persons = persons;
        this.times = times;
        int len = times.length;
        winPerson = new int[len];
        int max = 0;
        int curWin = -1;
        int[] count = new int[len];
        for (int i = 0; i < len; i++) {
            if (++count[persons[i]] >= max) {
                max = count[persons[i]];
                curWin = persons[i];
            }
            winPerson[i] = curWin;
        }
    }

    public int q(int t) {
        int left = 0, right = times.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (times[mid] <= t) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return winPerson[left];
    }
}