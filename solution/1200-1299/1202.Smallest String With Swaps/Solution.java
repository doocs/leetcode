class Solution {
    private int[] p;

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        p = new int[n];
        List<Character>[] d = new List[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            d[i] = new ArrayList<>();
        }
        for (var pair : pairs) {
            int a = pair.get(0), b = pair.get(1);
            p[find(a)] = find(b);
        }
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; ++i) {
            d[find(i)].add(cs[i]);
        }
        for (var e : d) {
            e.sort((a, b) -> b - a);
        }
        for (int i = 0; i < n; ++i) {
            var e = d[find(i)];
            cs[i] = e.remove(e.size() - 1);
        }
        return String.valueOf(cs);
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}