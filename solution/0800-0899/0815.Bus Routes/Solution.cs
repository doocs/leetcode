public class Solution {
    public int NumBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        Dictionary<int, List<int>> g = new Dictionary<int, List<int>>();
        for (int i = 0; i < routes.Length; i++) {
            foreach (int stop in routes[i]) {
                if (!g.ContainsKey(stop)) {
                    g[stop] = new List<int>();
                }
                g[stop].Add(i);
            }
        }

        if (!g.ContainsKey(source) || !g.ContainsKey(target)) {
            return -1;
        }

        Queue<int[]> q = new Queue<int[]>();
        HashSet<int> visBus = new HashSet<int>();
        HashSet<int> visStop = new HashSet<int>();
        q.Enqueue(new int[]{source, 0});
        visStop.Add(source);

        while (q.Count > 0) {
            int[] current = q.Dequeue();
            int stop = current[0], busCount = current[1];
            if (stop == target) {
                return busCount;
            }
            foreach (int bus in g[stop]) {
                if (!visBus.Contains(bus)) {
                    foreach (int nextStop in routes[bus]) {
                        if (!visStop.Contains(nextStop)) {
                            visBus.Add(bus);
                            visStop.Add(nextStop);
                            q.Enqueue(new int[]{nextStop, busCount + 1});
                        }
                    }
                }
            }
        }

        return -1;
    }
}
