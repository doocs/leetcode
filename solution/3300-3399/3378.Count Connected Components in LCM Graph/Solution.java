class DSU {
    private Map<Integer, Integer> parent;
    private Map<Integer, Integer> rank;

    public DSU(int n) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            parent.put(i, i);
            rank.put(i, 0);
        }
    }

    public void makeSet(int v) {
        parent.put(v, v);
        rank.put(v, 1);
    }

    public int find(int x) {
        if (parent.get(x) != x) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    public void unionSet(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            if (rank.get(u) < rank.get(v)) {
                int temp = u;
                u = v;
                v = temp;
            }
            parent.put(v, u);
            if (rank.get(u).equals(rank.get(v))) {
                rank.put(u, rank.get(u) + 1);
            }
        }
    }
}

class Solution {
    public int countComponents(int[] nums, int threshold) {
        DSU dsu = new DSU(threshold);

        for (int num : nums) {
            for (int j = num; j <= threshold; j += num) {
                dsu.unionSet(num, j);
            }
        }

        Set<Integer> uniqueParents = new HashSet<>();
        for (int num : nums) {
            if (num > threshold) {
                uniqueParents.add(num);
            } else {
                uniqueParents.add(dsu.find(num));
            }
        }

        return uniqueParents.size();
    }
}
