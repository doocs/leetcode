class Solution {
    private Map<String, Integer> mp = new HashMap<>();
    private Map<String, String> p = new HashMap<>();

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        for (String e : names) {
            int idx = e.indexOf("(");
            String name = e.substring(0, idx);
            int w = Integer.parseInt(e.substring(idx + 1, e.length() - 1));
            mp.put(name, w);
            p.put(name, name);
        }
        for (String e : synonyms) {
            int idx = e.indexOf(",");
            String name1 = e.substring(1, idx);
            String name2 = e.substring(idx + 1, e.length() - 1);
            if (!mp.containsKey(name1)) {
                mp.put(name1, 0);
            }
            if (!mp.containsKey(name2)) {
                mp.put(name2, 0);
            }
            p.put(name1, name1);
            p.put(name2, name2);
        }
        for (String e : synonyms) {
            int idx = e.indexOf(",");
            String name1 = e.substring(1, idx);
            String name2 = e.substring(idx + 1, e.length() - 1);
            union(name1, name2);
        }
        List<String> t = new ArrayList<>();
        for (Map.Entry<String, Integer> e : mp.entrySet()) {
            String name = e.getKey();
            if (Objects.equals(name, find(name))) {
                t.add(name + "(" + e.getValue() + ")");
            }
        }
        String[] res = new String[t.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = t.get(i);
        }
        return res;
    }

    private String find(String x) {
        if (!Objects.equals(p.get(x), x)) {
            p.put(x, find(p.get(x)));
        }
        return p.get(x);
    }

    private void union(String a, String b) {
        String pa = find(a), pb = find(b);
        if (Objects.equals(pa, pb)) {
            return;
        }
        if (pa.compareTo(pb) > 0) {
            mp.put(pb, mp.getOrDefault(pb, 0) + mp.getOrDefault(pa, 0));
            p.put(pa, pb);
        } else {
            mp.put(pa, mp.getOrDefault(pa, 0) + mp.getOrDefault(pb, 0));
            p.put(pb, pa);
        }
    }
}