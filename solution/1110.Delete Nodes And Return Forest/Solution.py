'''
https://leetcode.com/problems/delete-nodes-and-return-forest/
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

'''
# Performance
'''
Runtime: 52 ms, faster than 99.34% of Python3 online submissions for Delete Nodes And Return Forest.
Memory Usage: 12.8 MB, less than 100.00% of Python3 online submissions for Delete Nodes And Return Forest.
'''
'''
n: number of node
m: number of to_delete
Runtime: O(n)
Space: O(log n) or O(m)
'''


class Solution:
    def delNodes(self, root, to_delete):
        # Change to set to for better check runtime
        set_delete = set(to_delete)
        forests = []
        # Only add a node in the forest when
        # 1) Its parent is in set_delete
        # 2) It's not in set_delete
        # dfs return False when 1) Node is none and 2) node is in set_delete
        # When that happen, set the parent link to that node to None
        
        def dfs(node, is_parent_in_delete):
            nonlocal set_delete, forests
            if not node:
                return False
            # Still traverse subsequent node but return False
            if node.val in set_delete:
                if node.left:
                    dfs(node.left, True)
                if node.right:
                    dfs(node.right, True)
                return False
            # Add the current node to the forest
            if is_parent_in_delete:
                forests.append(node)
            # Set the child to none when the child is in set_delete
            if node.left and not dfs(node.left, False):
                node.left = None
            if node.right and not dfs(node.right, False):
                node.right = None
            return True
        dfs(root, True)
        return forests
