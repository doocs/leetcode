class LUPrefix {
    private int r;
    private Set<Integer> s = new HashSet<>();

    public LUPrefix(int n) {
    }

    public void upload(int video) {
        s.add(video);
        while (s.contains(r + 1)) {
            ++r;
        }
    }

    public int longest() {
        return r;
    }
}

/**
 * Your LUPrefix object will be instantiated and called as such:
 * LUPrefix obj = new LUPrefix(n);
 * obj.upload(video);
 * int param_2 = obj.longest();
 */