public class Solution {
    public int NumBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        // Use Dictionary to map stops to bus routes
        var g = new Dictionary<int, List<int>>();
        for (int i = 0; i < routes.Length; i++) {
            foreach (int stop in routes[i]) {
                if (!g.ContainsKey(stop)) {
                    g[stop] = new List<int>();
                }
                g[stop].Add(i);
            }
        }

        // If source or target is not in the mapping, return -1
        if (!g.ContainsKey(source) || !g.ContainsKey(target)) {
            return -1;
        }

        // Initialize queue and visited sets
        var q = new Queue<int[]>();
        var visBus = new HashSet<int>();
        var visStop = new HashSet<int>();
        q.Enqueue(new int[] { source, 0 });
        visStop.Add(source);

        // Begin BFS
        while (q.Count > 0) {
            var current = q.Dequeue();
            int stop = current[0], busCount = current[1];

            // If the current stop is the target stop, return the bus count
            if (stop == target) {
                return busCount;
            }

            // Traverse all bus routes passing through the current stop
            foreach (int bus in g[stop]) {
                if (visBus.Add(bus)) {
                    // Traverse all stops on this bus route
                    foreach (int nextStop in routes[bus]) {
                        if (visStop.Add(nextStop)) {
                            q.Enqueue(new int[] { nextStop, busCount + 1 });
                        }
                    }
                }
            }
        }

        return -1; // If unable to reach the target stop, return -1
    }
}
