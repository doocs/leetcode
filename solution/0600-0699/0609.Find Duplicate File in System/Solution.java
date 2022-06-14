class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] a = path.split(" ");
            for (int i = 1; i < a.length; i++) {
                int j = a[i].indexOf('(');
                String content = a[i].substring(j + 1, a[i].length() - 1);
                String name = a[0] + '/' + a[i].substring(0, j);
                List<String> list = map.getOrDefault(content, new ArrayList<>());
                list.add(name);
                map.put(content, list);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for (List<String> names : map.values()) {
            if (names.size() > 1) {
                ans.add(names);
            }
        }
        return ans;
    }
}
