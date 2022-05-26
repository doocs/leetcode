class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> mp = new HashMap<>();
        for (int i = 0; i < list2.length; ++i) {
            mp.put(list2[i], i);
        }
        List<String> ans = new ArrayList<>();
        int mi = 2000;
        for (int i = 0; i < list1.length; ++i) {
            if (mp.containsKey(list1[i])) {
                int t = i + mp.get(list1[i]);
                if (t < mi) {
                    ans = new ArrayList<>();
                    ans.add(list1[i]);
                    mi = t;
                } else if (t == mi) {
                    ans.add(list1[i]);
                }
            }
        }
        return ans.toArray(new String[0]);
    }
}