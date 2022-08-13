class TopVotedCandidate {
public:
    vector<int> times;
    vector<int> wins;

    TopVotedCandidate(vector<int>& persons, vector<int>& times) {
        int n = persons.size();
        wins.resize(n);
        int mx = 0, cur = 0;
        this->times = times;
        vector<int> counter(n);
        for (int i = 0; i < n; ++i) {
            int p = persons[i];
            if (++counter[p] >= mx) {
                mx = counter[p];
                cur = p;
            }
            wins[i] = cur;
        }
    }

    int q(int t) {
        int left = 0, right = wins.size() - 1;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (times[mid] <= t)
                left = mid;
            else
                right = mid - 1;
        }
        return wins[left];
    }
};

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate* obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj->q(t);
 */