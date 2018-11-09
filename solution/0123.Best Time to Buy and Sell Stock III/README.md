## 买卖股票的最佳时机 III

### 问题描述
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:
```
输入: [3,3,5,0,0,3,1,4]
输出: 6
解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
```
示例 2:
```
输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
```
示例 3:
```
输入: [7,6,4,3,1] 
输出: 0 
解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
```
-------------
### 思路：

建立两个数组，因为是只能两次交易，所以有左右数组

- 一个数组是`left[i]`,表示在第i天及之前交易的最大利润
- 一个数组是`right[i]`,表示在第i天及之后交易的最大利润

最后同时遍历，求和取出最大值就可以了

```CPP
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
```