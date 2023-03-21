class Solution {
    private Map<String, List<String>> g = new HashMap<>();
    private Map<String, Integer> cnt = new HashMap<>();
    private Set<String> vis = new HashSet<>();
    private int freq;

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        for (String pairs : synonyms) {
            String[] pair = pairs.substring(1, pairs.length() - 1).split(",");
            String a = pair[0], b = pair[1];
            g.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            g.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        Set<String> s = new HashSet<>();
        for (String x : names) {
            int i = x.indexOf('(');
            String name = x.substring(0, i);
            s.add(name);
            cnt.put(name, Integer.parseInt(x.substring(i + 1, x.length() - 1)));
        }
        List<String> res = new ArrayList<>();
        for (String name : s) {
            if (!vis.contains(name)) {
                freq = 0;
                name = dfs(name);
                res.add(name + "(" + freq + ")");
            }
        }
        return res.toArray(new String[0]);
    }

    private String dfs(String a) {
        String mi = a;
        vis.add(a);
        freq += cnt.getOrDefault(a, 0);
        for (String b : g.getOrDefault(a, new ArrayList<>())) {
            if (!vis.contains(b)) {
                String t = dfs(b);
                if (t.compareTo(mi) < 0) {
                    mi = t;
                }
            }
        }
        return mi;
    }
}