class TrieNode:
    def __init__(self):
        self.children = collections.defaultdict(TrieNode)
        self.key = ""
        self.deleted = False


class Solution:
    def deleteDuplicateFolder(self, paths: List[List[str]]) -> List[List[str]]:
        root = TrieNode()

        for path in paths:
            current = root
            for folder in path:
                current = current.children[folder]
                current.key = folder

        seen = collections.defaultdict(list)

        def dfs(node):
            if not node.children:
                return ""
            keys = []
            for key, child in node.children.items():
                serialized = dfs(child)
                keys.append(f"{key}({serialized})")
            keys.sort()
            serialized = "".join(keys)
            if len(seen[serialized]) > 0:
                for duplicate in seen[serialized]:
                    duplicate.deleted = True
                node.deleted = True
            seen[serialized].append(node)
            return serialized

        dfs(root)

        result = []
        path = []

        def collect(node):
            if node.deleted:
                return
            if path:
                result.append(path.copy())
            for key, child in node.children.items():
                path.append(key)
                collect(child)
                path.pop()

        collect(root)
        return result
