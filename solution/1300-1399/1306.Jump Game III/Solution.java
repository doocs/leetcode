class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int i = q.poll();
            if (arr[i] == 0) {
                return true;
            }
            for (int j : Arrays.asList(i + arr[i], i - arr[i])) {
                if (j >= 0 && j < n && arr[j] >= 0) {
                    q.offer(j);
                }
            }
            arr[i] = -1;
        }
        return false;
    }
}