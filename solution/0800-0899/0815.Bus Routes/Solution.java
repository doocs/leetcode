class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                g.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }

        if (!g.containsKey(source) || !g.containsKey(target)) {
            return -1;
        }

        Deque<int[]> q = new ArrayDeque<>();
        Set<Integer> visBus = new HashSet<>();
        Set<Integer> visStop = new HashSet<>();
        q.offer(new int[] {source, 0});
        visStop.add(source);

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int stop = current[0], busCount = current[1];

            if (stop == target) {
                return busCount;
            }
            for (int bus : g.get(stop)) {
                if (visBus.add(bus)) {
                    for (int nextStop : routes[bus]) {
                        if (visStop.add(nextStop)) {
                            q.offer(new int[] {nextStop, busCount + 1});
                        }
                    }
                }
            }
        }

        return -1;
    }
}
