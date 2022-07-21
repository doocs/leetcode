class Trie {
    Map<String, Trie> children = new HashMap<>();
    int v;

    boolean insert(String w, int v) {
        Trie node = this;
        String[] ps = w.split("/");
        for (int i = 1; i < ps.length - 1; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                return false;
            }
            node = node.children.get(p);
        }
        if (node.children.containsKey(ps[ps.length - 1])) {
            return false;
        }
        node.children.put(ps[ps.length - 1], new Trie());
        node = node.children.get(ps[ps.length - 1]);
        node.v = v;
        return true;
    }

    int search(String w) {
        Trie node = this;
        String[] ps = w.split("/");
        for (int i = 1; i < ps.length; ++i) {
            String p = ps[i];
            if (!node.children.containsKey(p)) {
                return -1;
            }
            node = node.children.get(p);
        }
        return node.v == 0 ? -1 : node.v;
    }
}

class FileSystem {
    private Trie trie = new Trie();

    public FileSystem() {

    }
    
    public boolean createPath(String path, int value) {
        return trie.insert(path, value);
    }
    
    public int get(String path) {
        return trie.search(path);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */