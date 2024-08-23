from collections import defaultdict

class TrieNode:
    def __init__(self):
        self.children = defaultdict(TrieNode)
        self.id = None

class Solution:
    def deleteDuplicateFolder(self, paths):
        # Step 1: Build a Trie (Tree) to represent the file system structure
        root = TrieNode()
        
        for path in paths:
            node = root
            for folder in path:
                node = node.children[folder]
        
        # Step 2: Assign unique IDs to the subtrees for easier comparison
        subtree_map = defaultdict(list)
        def get_id(node):
            if not node.children:
                return 0  # Empty subtree
            subtree_tuple = tuple((name, get_id(child)) for name, child in sorted(node.children.items()))
            subtree_id = hash(subtree_tuple)
            subtree_map[subtree_id].append(node)
            return subtree_id
        
        get_id(root)
        
        # Step 3: Mark duplicate subtrees by examining the subtree map
        duplicates = set()
        for nodes in subtree_map.values():
            if len(nodes) > 1:  # If more than one node has the same subtree ID, mark as duplicate
                for node in nodes:
                    duplicates.add(node)
        
        # Step 4: Reconstruct the remaining paths
        ans = []
        
        def collect_paths(node, path):
            if node in duplicates:
                return  # Skip this subtree because it's marked for deletion
            if path:
                ans.append(path[:])
            for folder, child in sorted(node.children.items()):
                path.append(folder)
                collect_paths(child, path)
                path.pop()
        
        collect_paths(root, [])
        
        return ans
