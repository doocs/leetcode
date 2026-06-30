class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] indeg = new int[n];
        for (int c : leftChild) {
            if (c != -1) {
                indeg[c]++;
            }
        }
        for (int c : rightChild) {
            if (c != -1) {
                indeg[c]++;
            }
        }

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) {
                root = i;
                break;
            }
        }
        if (root == -1) {
            return false;
        }

        Deque<Integer> q = new ArrayDeque<>();
        q.add(root);
        Set<Integer> vis = new HashSet<>();
        vis.add(root);

        while (!q.isEmpty()) {
            int i = q.poll();
            int j = leftChild[i];
            if (j != -1) {
                if (vis.contains(j)) {
                    return false;
                }
                vis.add(j);
                q.add(j);
            }

            j = rightChild[i];
            if (j != -1) {
                if (vis.contains(j)) {
                    return false;
                }
                vis.add(j);
                q.add(j);
            }
        }

        return vis.size() == n;
    }
}