class Solution {
    private Map<Integer, Integer> p;
    private Map<Integer, Integer> size;
    private int mx;
    private int n;

    public int[] groupStrings(String[] words) {
        p = new HashMap<>();
        size = new HashMap<>();
        n = words.length;
        mx = 0;
        for (String word : words) {
            int x = 0;
            for (char c : word.toCharArray()) {
                x |= 1 << (c - 'a');
            }
            p.put(x, x);
            size.put(x, size.getOrDefault(x, 0) + 1);
            mx = Math.max(mx, size.get(x));
            if (size.get(x) > 1) {
                --n;
            }
        }
        for (int x : p.keySet()) {
            for (int i = 0; i < 26; ++i) {
                union(x, x ^ (1 << i));
                if (((x >> i) & 1) != 0) {
                    for (int j = 0; j < 26; ++j) {
                        if (((x >> j) & 1) == 0) {
                            union(x, x ^ (1 << i) | (1 << j));
                        }
                    }
                }
            }
        }
        return new int[] {n, mx};
    }

    private int find(int x) {
        if (p.get(x) != x) {
            p.put(x, find(p.get(x)));
        }
        return p.get(x);
    }

    private void union(int a, int b) {
        if (!p.containsKey(b)) {
            return;
        }
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return;
        }
        p.put(pa, pb);
        size.put(pb, size.get(pb) + size.get(pa));
        mx = Math.max(mx, size.get(pb));
        --n;
    }
}