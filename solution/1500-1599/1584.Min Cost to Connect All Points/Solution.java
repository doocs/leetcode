class Solution {
    static class Edge {

        int x;
        int y;
        int len;

        Edge(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }

    public int minCostConnectPoints(int[][] points) {
        Queue<Edge> heap = new PriorityQueue<>(Comparator.comparingInt((Edge e) -> e.len));
        boolean[] marked = new boolean[points.length];
        marked[0] = true;
        addVertex(points, marked, 0, heap);
        int count = 1;
        int res = 0;
        while (!heap.isEmpty()) {
            Edge edge = heap.poll();
            if (!marked[edge.y]) {
                res += edge.len;
                marked[edge.y] = true;
                addVertex(points, marked, edge.y, heap);
                count++;
                if (count == points.length) {
                    break;
                }
            }
        }
        return res;
    }

    public void addVertex(int[][] points, boolean[] marked, int x, Queue<Edge> heap) {
        for (int i = 0; i < marked.length; i++) {
            if (marked[i]) {
                continue;
            }
            heap.add(new Edge(x, i,
                    Math.abs(points[x][0] - points[i][0]) + Math.abs(points[x][1] - points[i][1])));
        }
    }
}