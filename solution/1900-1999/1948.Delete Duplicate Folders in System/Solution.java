class Solution {
    static class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();
        String name;
        boolean isDeleted = false;

        TrieNode(String name) {
            this.name = name;
        }
    }

    Map<String, List<TrieNode>> serialMap = new HashMap<>();

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        TrieNode root = new TrieNode("");
        
        // Step 1: Build the folder trie
        for (List<String> path : paths) {
            TrieNode curr = root;
            for (String folder : path) {
                curr.children.putIfAbsent(folder, new TrieNode(folder));
                curr = curr.children.get(folder);
            }
        }

        // Step 2: Serialize and collect duplicates
        serialize(root);

        // Step 3: Mark duplicate folders
        for (List<TrieNode> group : serialMap.values()) {
            if (group.size() > 1) {
                for (TrieNode node : group) {
                    node.isDeleted = true;
                }
            }
        }

        // Step 4: Collect remaining paths
        List<List<String>> result = new ArrayList<>();
        dfs(root, new ArrayList<>(), result);
        return result;
    }

    private String serialize(TrieNode node) {
        if (node.children.isEmpty()) return "";

        List<String> parts = new ArrayList<>();
        for (String childName : node.children.keySet().stream().sorted().toList()) {
            TrieNode child = node.children.get(childName);
            String sub = serialize(child);
            parts.add(childName + "[" + sub + "]");
        }

        String serial = String.join("", parts);
        serialMap.computeIfAbsent(serial, k -> new ArrayList<>()).add(node);
        return serial;
    }

    private void dfs(TrieNode node, List<String> path, List<List<String>> result) {
        for (TrieNode child : node.children.values()) {
            if (!child.isDeleted) {
                path.add(child.name);
                result.add(new ArrayList<>(path));
                dfs(child, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}
