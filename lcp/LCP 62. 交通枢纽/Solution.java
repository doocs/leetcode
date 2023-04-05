class Solution {
    public int transportationHub(int[][] path) {
        int[] ind = new int[1001];
        int[] outd = new int[1001];
        Set<Integer> s = new HashSet<>();
        for (int[] p : path) {
            int a = p[0], b = p[1];
            s.add(a);
            s.add(b);
            ind[b]++;
            outd[a]++;
        }
        for (int c : s) {
            if (ind[c] == s.size() - 1 && outd[c] == 0) {
                return c;
            }
        }
        return -1;
    }
}