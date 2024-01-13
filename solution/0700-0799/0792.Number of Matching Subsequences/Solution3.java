class Solution {
    private List<Integer>[] d = new List[26];

    public int numMatchingSubseq(String s, String[] words) {
        Arrays.setAll(d, k -> new ArrayList<>());
        for (int i = 0; i < s.length(); ++i) {
            d[s.charAt(i) - 'a'].add(i);
        }
        int ans = 0;
        for (String w : words) {
            if (check(w)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(String w) {
        int i = -1;
        for (int k = 0; k < w.length(); ++k) {
            int c = w.charAt(k) - 'a';
            int j = search(d[c], i);
            if (j == d[c].size()) {
                return false;
            }
            i = d[c].get(j);
        }
        return true;
    }

    private int search(List<Integer> t, int x) {
        int left = 0, right = t.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (t.get(mid) > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}