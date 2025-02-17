class Solution {
public:
    long long maxWeight(vector<int>& pizzas) {
        int n = pizzas.size();
        int days = pizzas.size() / 4;
        ranges::sort(pizzas);
        int odd = (days + 1) / 2;
        int even = days - odd;
        long long ans = accumulate(pizzas.begin() + n - odd, pizzas.end(), 0LL);
        for (int i = n - odd - 2; even; --even) {
            ans += pizzas[i];
            i -= 2;
        }
        return ans;
    }
};
