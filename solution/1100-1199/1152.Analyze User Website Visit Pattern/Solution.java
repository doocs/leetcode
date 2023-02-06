class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Node>> d = new HashMap<>();
        int n = username.length;
        for (int i = 0; i < n; ++i) {
            String user = username[i];
            int ts = timestamp[i];
            String site = website[i];
            d.computeIfAbsent(user, k -> new ArrayList<>()).add(new Node(user, ts, site));
        }
        Map<String, Integer> cnt = new HashMap<>();
        for (var sites : d.values()) {
            int m = sites.size();
            Set<String> s = new HashSet<>();
            if (m > 2) {
                Collections.sort(sites, (a, b) -> a.ts - b.ts);
                for (int i = 0; i < m - 2; ++i) {
                    for (int j = i + 1; j < m - 1; ++j) {
                        for (int k = j + 1; k < m; ++k) {
                            s.add(sites.get(i).site + "," + sites.get(j).site + ","
                                + sites.get(k).site);
                        }
                    }
                }
            }
            for (String t : s) {
                cnt.put(t, cnt.getOrDefault(t, 0) + 1);
            }
        }
        int mx = 0;
        String t = "";
        for (var e : cnt.entrySet()) {
            if (mx < e.getValue() || (mx == e.getValue() && e.getKey().compareTo(t) < 0)) {
                mx = e.getValue();
                t = e.getKey();
            }
        }
        return Arrays.asList(t.split(","));
    }
}

class Node {
    String user;
    int ts;
    String site;

    Node(String user, int ts, String site) {
        this.user = user;
        this.ts = ts;
        this.site = site;
    }
}