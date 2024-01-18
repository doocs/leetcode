class Solution {
    public int[] shortestSeq(int[] big, int[] small) {
        int cnt = small.length;
        Map<Integer, Integer> need = new HashMap<>(cnt);
        Map<Integer, Integer> window = new HashMap<>(cnt);
        for (int x : small) {
            need.put(x, 1);
        }
        int k = -1, mi = 1 << 30;
        for (int i = 0, j = 0; i < big.length; ++i) {
            window.merge(big[i], 1, Integer::sum);
            if (need.getOrDefault(big[i], 0) >= window.get(big[i])) {
                --cnt;
            }
            while (cnt == 0) {
                if (i - j + 1 < mi) {
                    mi = i - j + 1;
                    k = j;
                }
                if (need.getOrDefault(big[j], 0) >= window.get(big[j])) {
                    ++cnt;
                }
                window.merge(big[j++], -1, Integer::sum);
            }
        }
        return k < 0 ? new int[0] : new int[] {k, k + mi - 1};
    }
}