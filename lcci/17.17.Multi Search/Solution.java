class Solution {
    public int[][] multiSearch(String big, String[] smalls) {
        Trie tree = new Trie();
        int n = smalls.length;
        for (int i = 0; i < n; ++i) {
            tree.insert(smalls[i], i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            res.add(new ArrayList<>());
        }
        for (int i = 0; i < big.length(); ++i) {
            String s = big.substring(i);
            List<Integer> t = tree.search(s);
            for (int idx : t) {
                res.get(idx).add(i);
            }
        }
        int[][] ans = new int[n][];
        for (int i = 0; i < n; ++i) {
            ans[i] = res.get(i).stream().mapToInt(Integer::intValue).toArray();
        }
        return ans;
    }
}

class Trie {
    private int idx;
    private Trie[] children;

    public Trie() {
        idx = -1;
        children = new Trie[26];
    }

    public void insert(String word, int i) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.idx = i;
    }

    public List<Integer> search(String word) {
        Trie node = this;
        List<Integer> res = new ArrayList<>();
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return res;
            }
            node = node.children[c];
            if (node.idx != -1) {
                res.add(node.idx);
            }
        }
        return res;
    }
}