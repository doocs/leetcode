public class Solution {
    public int NumBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        
        Dictionary<int, HashSet<int>> stopToRoutes = new Dictionary<int, HashSet<int>>();
        List<HashSet<int>> routeToStops = new List<HashSet<int>>();
        
        for (int i = 0; i < routes.Length; i++) {
            routeToStops.Add(new HashSet<int>());
            foreach (int stop in routes[i]) {
                routeToStops[i].Add(stop);
                if (!stopToRoutes.ContainsKey(stop)) {
                    stopToRoutes[stop] = new HashSet<int>();
                }
                stopToRoutes[stop].Add(i);
            }
        }
        
        Queue<int> queue = new Queue<int>();
        HashSet<int> visited = new HashSet<int>();
        int ans = 0;

        foreach (int routeId in stopToRoutes[source]) {
            queue.Enqueue(routeId);
            visited.Add(routeId);
        }

        while (queue.Count > 0) {
            int count = queue.Count;
            ans++;

            for (int i = 0; i < count; i++) {
                int routeId = queue.Dequeue();

                foreach (int stop in routeToStops[routeId]) {
                    if (stop == target) {
                        return ans;
                    }

                    foreach (int nextRoute in stopToRoutes[stop]) {
                        if (!visited.Contains(nextRoute)) {
                            visited.Add(nextRoute);
                            queue.Enqueue(nextRoute);
                        }
                    }
                }
            }
        }

        return -1;
    }
}
