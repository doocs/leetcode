class Solution {
public:
    int buyChoco(vector<int>& prices, int money) {
        sort(prices.begin(),prices.end());
        int price= prices[0]+prices[1];
        if(price <= money)
        {
            return money-price;
        }
        return money;
    }
};