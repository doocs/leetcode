class Solution {
    public String destCity(List<List<String>> paths) {
        Map<String, String> mp = new HashMap<>();
        for (List<String> path : paths) {
            mp.put(path.get(0), path.get(1));
        }
        String a = paths.get(0).get(0);
        while (mp.get(a) != null) {
            a = mp.get(a);
        }
        return a;
    }
}