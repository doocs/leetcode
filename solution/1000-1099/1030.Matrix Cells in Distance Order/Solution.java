class Solution {
    class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] ans = new int[R * C][2];
        int[][] flag = new int[R][C];
        int index = 0;

        ans[index][0] = r0;
        ans[index][1] = c0;
        index++;
        flag[r0][c0] = 1;

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(new Node(r0, c0));
        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();

            // up
            if (node.r - 1 >= 0 && flag[node.r - 1][node.c] != 1) {
                queue.add(new Node(node.r - 1, node.c));
                flag[node.r - 1][node.c] = 1;
                ans[index][0] = node.r - 1;
                ans[index][1] = node.c;
                index++;
            }

            // down
            if (node.r + 1 < R && flag[node.r + 1][node.c] != 1) {
                queue.add(new Node(node.r + 1, node.c));
                flag[node.r + 1][node.c] = 1;
                ans[index][0] = node.r + 1;
                ans[index][1] = node.c;
                index++;
            }

            // left
            if (node.c - 1 >= 0 && flag[node.r][node.c - 1] != 1) {
                queue.add(new Node(node.r, node.c - 1));
                flag[node.r][node.c - 1] = 1;
                ans[index][0] = node.r;
                ans[index][1] = node.c - 1;
                index++;
            }

            // right
            if (node.c + 1 < C && flag[node.r][node.c + 1] != 1) {
                queue.add(new Node(node.r, node.c + 1));
                flag[node.r][node.c + 1] = 1;
                ans[index][0] = node.r;
                ans[index][1] = node.c + 1;
                index++;
            }
        }

        return ans;
    }
}
