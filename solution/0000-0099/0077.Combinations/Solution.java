class Solution {

    private List<List<Integer>> result;

    public List<List<Integer>> combine(int n, int k) {
        result = new ArrayList<>();
        combine(n-k+1,k,0,1,new Integer[k]);
        return result;
    }

    private void combine(int n, int k, int i, int start, Integer[] list) {
        if (i==k) {
            result.add(new ArrayList<>(Arrays.asList(list)));
            return;
        }
        for (int j = start; j <= n+i; j++) {
            list[i] = j;
            combine(n,k,i+1,j+1,list);
        }
    }
}