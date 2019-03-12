class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> re = new ArrayList<>();
        if (numRows == 0) return re;
        re.add(Collections.singletonList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            re.add(list);
            for (int j = 0; j < i + 1; j++) {
                int l = j - 1 < 0 ? 0 : re.get(i - 1).get(j - 1);
                int r = j > i - 1 ? 0 : re.get(i - 1).get(j);
                list.add(l + r);
            }
        }
        return re;
    }
}