class Trie {
    Trie[] children = new Trie[27];
    int v;
    String w = "";

    void insert(String w, int t) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            int idx = c == ' ' ? 26 : c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.v += t;
        node.w = w;
    }

    Trie search(String pref) {
        Trie node = this;
        for (char c : pref.toCharArray()) {
            int idx = c == ' ' ? 26 : c - 'a';
            if (node.children[idx] == null) {
                return null;
            }
            node = node.children[idx];
        }
        return node;
    }
}

class AutocompleteSystem {
    private Trie trie = new Trie();
    private StringBuilder t = new StringBuilder();

    public AutocompleteSystem(String[] sentences, int[] times) {
        int i = 0;
        for (String s : sentences) {
            trie.insert(s, times[i++]);
        }
    }
    
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            trie.insert(t.toString(), 1);
            t = new StringBuilder();
            return res;
        }
        t.append(c);
        Trie node = trie.search(t.toString());
        if (node == null) {
            return res;
        }
        PriorityQueue<Trie> q = new PriorityQueue<>((a, b) -> a.v == b.v ? b.w.compareTo(a.w) : a.v - b.v);
        dfs(node, q);
        while (!q.isEmpty()) {
            res.add(0, q.poll().w);
        }
        return res;
    }

    private void dfs(Trie node, PriorityQueue q) {
        if (node == null) {
            return;
        }
        if (node.v > 0) {
            q.offer(node);
            if (q.size() > 3) {
                q.poll();
            }
        }
        for (Trie nxt : node.children) {
            dfs(nxt, q);
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */