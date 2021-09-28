class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<Integer> tables = new HashSet<>();
        Set<String> foods = new HashSet<>();
        Map<String, Integer> mp = new HashMap<>();
        for (List<String> order : orders) {
            int table = Integer.parseInt(order.get(1));
            String food = order.get(2);
            tables.add(table);
            foods.add(food);
            String key = table + "." + food;
            mp.put(key, mp.getOrDefault(key, 0) + 1);
        }
        List<Integer> t = new ArrayList<>(tables);
        List<String> f = new ArrayList<>(foods);
        Collections.sort(t);
        Collections.sort(f);
        List<List<String>> res = new ArrayList<>();
        List<String> title = new ArrayList<>();
        title.add("Table");
        title.addAll(f);
        res.add(title);
        for (int table : t) {
            List<String> tmp = new ArrayList<>();
            tmp.add(String.valueOf(table));
            for (String food : f) {
                tmp.add(String.valueOf(mp.getOrDefault(table + "." + food, 0)));
            }
            res.add(tmp);
        }
        return res;
    }
}