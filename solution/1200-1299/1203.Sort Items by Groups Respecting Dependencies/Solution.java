class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int idx = m;
        List<Integer>[] groupItems = new List[n + m];
        int[] itemDegree = new int[n];
        int[] groupDegree = new int[n + m];
        List<Integer>[] itemGraph = new List[n];
        List<Integer>[] groupGraph = new List[n + m];
        Arrays.setAll(groupItems, k -> new ArrayList<>());
        Arrays.setAll(itemGraph, k -> new ArrayList<>());
        Arrays.setAll(groupGraph, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            if (group[i] == -1) {
                group[i] = idx++;
            }
            groupItems[group[i]].add(i);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : beforeItems.get(i)) {
                if (group[i] == group[j]) {
                    ++itemDegree[i];
                    itemGraph[j].add(i);
                } else {
                    ++groupDegree[group[i]];
                    groupGraph[group[j]].add(group[i]);
                }
            }
        }
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < n + m; ++i) {
            items.add(i);
        }
        var groupOrder = topoSort(groupDegree, groupGraph, items);
        if (groupOrder.isEmpty()) {
            return new int[0];
        }
        List<Integer> ans = new ArrayList<>();
        for (int gi : groupOrder) {
            items = groupItems[gi];
            var itemOrder = topoSort(itemDegree, itemGraph, items);
            if (itemOrder.size() != items.size()) {
                return new int[0];
            }
            ans.addAll(itemOrder);
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private List<Integer> topoSort(int[] degree, List<Integer>[] graph, List<Integer> items) {
        Deque<Integer> q = new ArrayDeque<>();
        for (int i : items) {
            if (degree[i] == 0) {
                q.offer(i);
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int i = q.poll();
            ans.add(i);
            for (int j : graph[i]) {
                if (--degree[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return ans.size() == items.size() ? ans : List.of();
    }
}