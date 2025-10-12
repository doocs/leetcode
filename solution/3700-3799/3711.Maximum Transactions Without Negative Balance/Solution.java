class Solution {
    public int maxTransactions(int[] transactions) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int ans = transactions.length;
        long s = 0;
        for (int x : transactions) {
            s += x;
            tm.merge(x, 1, Integer::sum);
            while (s < 0) {
                int y = tm.firstKey();
                s -= y;
                --ans;
                if (tm.merge(y, -1, Integer::sum) == 0) {
                    tm.remove(y);
                }
            }
        }
        return ans;
    }
}
