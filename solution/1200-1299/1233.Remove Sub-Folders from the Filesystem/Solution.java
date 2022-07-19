class Trie {
    Map<String, Trie> children = new HashMap<>();
    boolean isEnd;

    void insert(String w) {
        Trie node = this;
        String[] ps = w.split("/");
        for (int i = 1; i < ps.length; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                node.children.put(p, new Trie());
            }
            node = node.children.get(p);
        }
        node.isEnd = true;
    }

    boolean search(String w) {
        Trie node = this;
        String[] ps = w.split("/");
        for (int i = 1; i < ps.length; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                return false;
            }
            node = node.children.get(p);
            if (node.isEnd) {
                return true;
            }
        }
        return false;
    }
}

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, (a, b) -> a.split("/").length - b.split("/").length);
        Trie trie = new Trie();
        List<String> ans = new ArrayList<>();
        for (String v : folder) {
            if (!trie.search(v)) {
                trie.insert(v);
                ans.add(v);
            }
        }
        return ans;
    }
}