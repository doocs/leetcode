class Solution {
    public int shoppingOffers(
        List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int ans = total(price, needs);
        List<Integer> t = new ArrayList<>();
        for (List<Integer> offer : special) {
            t.clear();
            for (int j = 0; j < needs.size(); ++j) {
                if (offer.get(j) > needs.get(j)) {
                    t.clear();
                    break;
                }
                t.add(needs.get(j) - offer.get(j));
            }
            if (!t.isEmpty()) {
                ans = Math.min(
                    ans, offer.get(offer.size() - 1) + shoppingOffers(price, special, t));
            }
        }
        return ans;
    }

    private int total(List<Integer> price, List<Integer> needs) {
        int s = 0;
        for (int i = 0; i < price.size(); ++i) {
            s += price.get(i) * needs.get(i);
        }
        return s;
    }
}