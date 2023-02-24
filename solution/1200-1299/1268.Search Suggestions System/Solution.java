class Trie {
    Trie[] children = new Trie[26];
    List<Integer> v = new ArrayList<>();

    public void insert(String w, int i) {
        Trie node = this;
        for (int j = 0; j < w.length(); ++j) {
            int idx = w.charAt(j) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            if (node.v.size() < 3) {
                node.v.add(i);
            }
        }
    }

    public List<Integer>[] search(String w) {
        Trie node = this;
        int n = w.length();
        List<Integer>[] ans = new List[n];
        Arrays.setAll(ans, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            int idx = w.charAt(i) - 'a';
            if (node.children[idx] == null) {
                break;
            }
            node = node.children[idx];
            ans[i] = node.v;
        }
        return ans;
    }
}

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        Trie trie = new Trie();
        for (int i = 0; i < products.length; ++i) {
            trie.insert(products[i], i);
        }
        List<List<String>> ans = new ArrayList<>();
        for (var v : trie.search(searchWord)) {
            List<String> t = new ArrayList<>();
            for (int i : v) {
                t.add(products[i]);
            }
            ans.add(t);
        }
        return ans;
    }
}