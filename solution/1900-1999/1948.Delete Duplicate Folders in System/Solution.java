import java.util.*;

class TrieNode {
    Map<String, TrieNode> children;
    Integer id;
    
    public TrieNode() {
        this.children = new HashMap<>();
        this.id = null;
    }
}

public class Solution {
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        TrieNode root = new TrieNode();
        
        // Step 1: Build Trie (Tree)
        for (List<String> path : paths) {
            TrieNode node = root;
            for (String folder : path) {
                node = node.children.computeIfAbsent(folder, k -> new TrieNode());
            }
        }
        
        // Step 2: Assign unique IDs to subtrees
        Map<String, List<TrieNode>> subtreeMap = new HashMap<>();
        
        getSubtreeId(root, subtreeMap);
        
        // Step 3: Mark duplicates
        Set<TrieNode> duplicates = new HashSet<>();
        for (List<TrieNode> nodes : subtreeMap.values()) {
            if (nodes.size() > 1) { // If more than one node has the same structure, mark as duplicate
                duplicates.addAll(nodes);
            }
        }
        
        // Step 4: Collect remaining paths
        List<List<String>> ans = new ArrayList<>();
        collectRemainingPaths(root, new ArrayList<>(), duplicates, ans);
        
        return ans;
    }
    
    private String getSubtreeId(TrieNode node, Map<String, List<TrieNode>> subtreeMap) {
        if (node.children.isEmpty()) {
            return ""; // Empty subtree has no ID
        }
        
        // Sort children lexicographically and form a string representing the subtree
        List<String> subtreeList = new ArrayList<>();
        for (Map.Entry<String, TrieNode> entry : node.children.entrySet()) {
            String folder = entry.getKey();
            TrieNode child = entry.getValue();
            subtreeList.add(folder + "(" + getSubtreeId(child, subtreeMap) + ")");
        }
        Collections.sort(subtreeList);
        
        // Create a unique string representation of the subtree
        String subtree = String.join(",", subtreeList);
        
        // Map the subtree representation to the node
        subtreeMap.computeIfAbsent(subtree, k -> new ArrayList<>()).add(node);
        
        return subtree;
    }
    
    private void collectRemainingPaths(TrieNode node, List<String> path, Set<TrieNode> duplicates, List<List<String>> ans) {
        if (duplicates.contains(node)) {
            return; // Skip this subtree because it is marked for deletion
        }
        
        if (!path.isEmpty()) {
            ans.add(new ArrayList<>(path));
        }
        
        for (Map.Entry<String, TrieNode> entry : node.children.entrySet()) {
            path.add(entry.getKey());
            collectRemainingPaths(entry.getValue(), path, duplicates, ans);
            path.remove(path.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        List<List<String>> paths1 = Arrays.asList(
                Arrays.asList("a"),
                Arrays.asList("c"),
                Arrays.asList("d"),
                Arrays.asList("a", "b"),
                Arrays.asList("c", "b"),
                Arrays.asList("d", "a")
        );
        
        List<List<String>> result1 = sol.deleteDuplicateFolder(paths1);
        for (List<String> path : result1) {
            System.out.println(path);
        }
        
        List<List<String>> paths2 = Arrays.asList(
                Arrays.asList("a"),
                Arrays.asList("c"),
                Arrays.asList("a", "b"),
                Arrays.asList("c", "b"),
                Arrays.asList("a", "b", "x"),
                Arrays.asList("a", "b", "x", "y"),
                Arrays.asList("w"),
                Arrays.asList("w", "y")
        );
        
        List<List<String>> result2 = sol.deleteDuplicateFolder(paths2);
        for (List<String> path : result2) {
            System.out.println(path);
        }
    }
}
