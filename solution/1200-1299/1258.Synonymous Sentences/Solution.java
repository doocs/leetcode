class Solution {
    private Map<String, String> p;

    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        p = new HashMap<>();
        for (List<String> item : synonyms) {
            p.put(item.get(0), item.get(0));
            p.put(item.get(1), item.get(1));
        }
        for (List<String> item : synonyms) {
            p.put(find(item.get(0)), find(item.get(1)));
        }
        Map<String, Set<String>> s = new HashMap<>();
        for (List<String> item : synonyms) {
            String a = find(item.get(0)), b = find(item.get(1));
            s.computeIfAbsent(a, k -> new HashSet<>()).add(item.get(0));
            s.computeIfAbsent(b, k -> new HashSet<>()).add(item.get(1));
        }
        List<List<String>> all = new ArrayList<>();
        for (String word : text.split(" ")) {
            if (!p.containsKey(word)) {
                if (all.isEmpty()) {
                    List<String> t = new ArrayList<>();
                    t.add(word);
                    all.add(t);
                } else {
                    for (List<String> a : all) {
                        a.add(word);
                    }
                }
            } else {
                Set<String> words = s.get(find(word));
                if (all.isEmpty()) {
                    for (String b : words) {
                        List<String> t = new ArrayList<>();
                        t.add(b);
                        all.add(t);
                    }
                } else {
                    List<List<String>> t = new ArrayList<>();
                    for (List<String> a : all) {
                        for (String b : words) {
                            List<String> c = new ArrayList<>(a);
                            c.add(b);
                            t.add(c);
                        }
                    }
                    all = t;
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (List<String> item : all) {
            res.add(String.join(" ", item));
        }
        Collections.sort(res);
        return res;
    }

    private String find(String x) {
        if (!Objects.equals(p.get(x), x)) {
            p.put(x, find(p.get(x)));
        }
        return p.get(x);
    }
}