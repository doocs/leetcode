class SnakeGame {
    private int m;
    private int n;
    private int[][] food;
    private int score;
    private int idx;
    private Deque<Integer> q = new ArrayDeque<>();
    private Set<Integer> vis = new HashSet<>();

    public SnakeGame(int width, int height, int[][] food) {
        m = height;
        n = width;
        this.food = food;
        q.offer(0);
        vis.add(0);
    }

    public int move(String direction) {
        int p = q.peekFirst();
        int i = p / n, j = p % n;
        int x = i, y = j;
        if ("U".equals(direction)) {
            --x;
        } else if ("D".equals(direction)) {
            ++x;
        } else if ("L".equals(direction)) {
            --y;
        } else {
            ++y;
        }
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return -1;
        }
        if (idx < food.length && x == food[idx][0] && y == food[idx][1]) {
            ++score;
            ++idx;
        } else {
            int t = q.pollLast();
            vis.remove(t);
        }
        int cur = f(x, y);
        if (vis.contains(cur)) {
            return -1;
        }
        q.offerFirst(cur);
        vis.add(cur);
        return score;
    }

    private int f(int i, int j) {
        return i * n + j;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */