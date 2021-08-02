class Solution {
    public long numberOfWeeks(int[] milestones) {
        int mx = 0;
        long s = 0;
        for (int e : milestones) {
            s += e;
            mx = Math.max(mx, e);
        }
        long rest = s - mx;
        return mx > rest + 1 ? rest * 2 + 1 : s;
    }
}