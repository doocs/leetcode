class Solution {

    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};


    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked.length < 2) {
            return Boolean.TRUE;
        }

        return walk(blocked, source, target) && walk(blocked, target, source);
    }

    private Boolean walk(int[][] blocked, int[] source, int[] target) {
        int N = 1000000;

        Set<Pair<Integer, Integer>> visitSet = new HashSet<>();
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        Pair<Integer, Integer> start = new Pair<>(source[0], source[1]);
        queue.add(start);
        visitSet.add(start);

        Set<Pair> blockedSet = Arrays.stream(blocked).map(item -> new Pair(item[0], item[1])).collect(Collectors.toSet());

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> top = queue.poll();
            Integer x = top.getKey();
            Integer y = top.getValue();

            for (int i = 0; i < 4; i++) {
                int newY = y + dy[i];
                int newX = x + dx[i];
                Pair<Integer, Integer> pair = new Pair<>(newX, newY);
                if (newX < 0 || newY < 0 || newX >= N || newY >= N || visitSet.contains(pair) || blockedSet.contains(pair)) {
                    continue;
                }
                queue.add(pair);
                visitSet.add(pair);
                if (queue.size() >= blocked.length || (newX == target[0] && newY == target[1])) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
}