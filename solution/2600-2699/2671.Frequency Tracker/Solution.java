class FrequencyTracker {
    private Map<Integer, Integer> cnt = new HashMap<>();
    private Map<Integer, Integer> freq = new HashMap<>();

    public FrequencyTracker() {
    }

    public void add(int number) {
        int f = cnt.getOrDefault(number, 0);
        if (freq.getOrDefault(f, 0) > 0) {
            freq.merge(f, -1, Integer::sum);
        }
        cnt.merge(number, 1, Integer::sum);
        freq.merge(f + 1, 1, Integer::sum);
    }

    public void deleteOne(int number) {
        int f = cnt.getOrDefault(number, 0);
        if (f == 0) {
            return;
        }
        freq.merge(f, -1, Integer::sum);
        cnt.merge(number, -1, Integer::sum);
        freq.merge(f - 1, 1, Integer::sum);
    }

    public boolean hasFrequency(int frequency) {
        return freq.getOrDefault(frequency, 0) > 0;
    }
}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * FrequencyTracker obj = new FrequencyTracker();
 * obj.add(number);
 * obj.deleteOne(number);
 * boolean param_3 = obj.hasFrequency(frequency);
 */