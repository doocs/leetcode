class FrequencyTracker {
public:
    FrequencyTracker() {
    }

    void add(int number) {
        freq[cnt[number]]--;
        cnt[number]++;
        freq[cnt[number]]++;
    }

    void deleteOne(int number) {
        if (cnt[number]) {
            freq[cnt[number]]--;
            cnt[number]--;
            freq[cnt[number]]++;
        }
    }

    bool hasFrequency(int frequency) {
        return freq[frequency] > 0;
    }

private:
    unordered_map<int, int> cnt;
    unordered_map<int, int> freq;
};

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * FrequencyTracker* obj = new FrequencyTracker();
 * obj->add(number);
 * obj->deleteOne(number);
 * bool param_3 = obj->hasFrequency(frequency);
 */