class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int left = 0;
        int len = prices.size();
        if(len == 0 || len == 1)return 0;
        
        vector<int> leftArr(len, 0);
	    vector<int> rightArr(len, 0);

	    int diff, day = 1, minPrice, maxPrice, maxProfit;

	    //计算某一天及之前的最大利益
	    minPrice = prices[0];
	    maxProfit = 0;
	    for (day = 1; day < len; day++) {
		    diff = prices[day] - minPrice;
		    if (diff < 0)minPrice = prices[day];
		    else if (diff > maxProfit)maxProfit = diff;

		    leftArr[day] = maxProfit;
	    }

	    //计算某一天及之前的最大利益
	    maxPrice = prices[len - 1];
	    maxProfit = 0;
	    for (day = len - 2; day >= 0; day--) {
		    diff = maxPrice - prices[day];
		    if (diff < 0)maxPrice = prices[day];
		    else if (diff > maxProfit)maxProfit = diff;

		    rightArr[day] = maxProfit;
        }

	    int sum = 0;
	    maxProfit = leftArr[0] + rightArr[0];
	    for (int i = 1; i < len; i++) {
		    sum = leftArr[i] + rightArr[i];
		    if (sum > maxProfit)maxProfit = sum;
	    }

	    return maxProfit;
    }
};