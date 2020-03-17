class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> re = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) re.add(i ^ (i >> 1));
        return re;
    }
}