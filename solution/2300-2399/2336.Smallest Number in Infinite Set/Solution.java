class SmallestInfiniteSet {
    private Set<Integer> black = new HashSet<>();

    public SmallestInfiniteSet() {
    }

    public int popSmallest() {
        int i = 1;
        for (; black.contains(i); ++i)
            ;
        black.add(i);
        return i;
    }

    public void addBack(int num) {
        black.remove(num);
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */