import java.util.*;

class Solution {
    static class Frame {
        int node, parent, left, depth, weight, old;
        boolean exit;

        Frame(int node, int parent, int left, int depth,
              int weight, int old, boolean exit) {
            this.node = node;
            this.parent = parent;
            this.left = left;
            this.depth = depth;
            this.weight = weight;
            this.old = old;
            this.exit = exit;
        }
    }

    public int[] longestSpecialPath(int[][] edges, int[] nums) {
        int n = nums.length;

        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            graph[e[1]].add(new int[]{e[0], e[2]});
        }

        Map<Integer, Integer> last = new HashMap<>();
        long[] prefix = new long[n + 1];

        Deque<Frame> stack = new ArrayDeque<>();
        stack.push(new Frame(0, -1, 0, 0, 0, 0, false));

        long bestLength = 0;
        int bestNodes = 1;

        while (!stack.isEmpty()) {
            Frame f = stack.pop();
            int u = f.node;

            if (f.exit) {
                if (f.old == 0) {
                    last.remove(nums[u]);
                } else {
                    last.put(nums[u], f.old);
                }
                continue;
            }

            prefix[f.depth + 1] = prefix[f.depth] + f.weight;

            int old = last.getOrDefault(nums[u], 0);
            int left = Math.max(f.left, old);

            last.put(nums[u], f.depth + 1);

            long length = prefix[f.depth + 1] - prefix[left + 1];
            int nodes = f.depth + 1 - left;

            if (length > bestLength ||
                (length == bestLength && nodes < bestNodes)) {
                bestLength = length;
                bestNodes = nodes;
            }

            stack.push(new Frame(
                u, f.parent, left, f.depth, 0, old, true
            ));

            for (int[] edge : graph[u]) {
                int v = edge[0];

                if (v != f.parent) {
                    stack.push(new Frame(
                        v, u, left, f.depth + 1,
                        edge[1], 0, false
                    ));
                }
            }
        }

        return new int[]{(int) bestLength, bestNodes};
    }
}
