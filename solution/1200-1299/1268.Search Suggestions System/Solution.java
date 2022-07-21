class Trie {
    Trie[] children = new Trie[26];
    List<Integer> v = new ArrayList<>();

    void insert(String word, int i) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
            if (node.v.size() < 3) {
                node.v.add(i);
            }
        }
    }

    List<List<Integer>> search(String word) {
        List<List<Integer>> res = new ArrayList<>();
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            res.add(new ArrayList<>());
        }
        Trie node = this;
        for (int i = 0; i < n; ++i) {
            char c = word.charAt(i);
            c -= 'a';
            if (node.children[c] == null) {
                break;
            }
            node = node.children[c];
            res.set(i, node.v);
        }
        return res;
    }
}

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        Trie trie = new Trie();
        for (int i = 0; i < products.length; ++i) {
            trie.insert(products[i], i);
        }
        List<List<Integer>> res = trie.search(searchWord);
        List<List<String>> ans = new ArrayList<>();
        for (List<Integer> v : res) {
            List<String> t = new ArrayList<>();
            for (int i : v) {
                t.add(products[i]);
            }
            ans.add(t);
        }
        return ans;
    }
}