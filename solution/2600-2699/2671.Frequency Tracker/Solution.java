class FrequencyTracker {
    private Map<Integer, Integer> cnt = new HashMap<>();
    private Map<Integer, Integer> freq = new HashMap<>();

    public FrequencyTracker() {
    }

    public void add(int number) {
        freq.merge(cnt.getOrDefault(number, 0), -1, Integer::sum);
        cnt.merge(number, 1, Integer::sum);
        freq.merge(cnt.get(number), 1, Integer::sum);
    }

    public void deleteOne(int number) {
        if (cnt.getOrDefault(number, 0) > 0) {
            freq.merge(cnt.get(number), -1, Integer::sum);
            cnt.merge(number, -1, Integer::sum);
            freq.merge(cnt.get(number), 1, Integer::sum);
        }
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