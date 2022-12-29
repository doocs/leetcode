class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        int k = 4 * cheeseSlices - tomatoSlices;
        int y = k / 2;
        int x = cheeseSlices - y;
        return k % 2 != 0 || y < 0 || x < 0 ? Collections.emptyList() : Arrays.asList(x, y);
    }
}