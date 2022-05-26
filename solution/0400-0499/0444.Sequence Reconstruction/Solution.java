class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int n = org.length;
        Set<Integer> nums = new HashSet<>();
        for (List<Integer> seq : seqs) {
            for (int num : seq) {
                if (num < 1 || num > n) {
                    return false;
                }
                nums.add(num);
            }
        }
        if (nums.size() < n) {
            return false;
        }
        List<Integer>[] edges = new List[n + 1];
        for (int i = 0; i < edges.length; ++i) {
            edges[i] = new ArrayList<>();
        }
        int[] indegree = new int[n + 1];
        for (List<Integer> seq : seqs) {
            int i = seq.get(0);
            for (int j = 1; j < seq.size(); ++j) {
                edges[i].add(seq.get(j));
                ++indegree[seq.get(j)];
                i = seq.get(j);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; ++i) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            if (q.size() > 1 || q.peek() != org[cnt]) {
                return false;
            }
            ++cnt;
            int i = q.poll();
            for (int j : edges[i]) {
                --indegree[j];
                if (indegree[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return cnt == n;
    }
}