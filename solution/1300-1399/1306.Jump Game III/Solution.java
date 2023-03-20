class Solution {
    public boolean canReach(int[] arr, int start) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int i = q.poll();
            if (arr[i] == 0) {
                return true;
            }
            int x = arr[i];
            arr[i] = -1;
            for (int j : List.of(i + x, i - x)) {
                if (j >= 0 && j < arr.length && arr[j] >= 0) {
                    q.offer(j);
                }
            }
        }
        return false;
    }
}