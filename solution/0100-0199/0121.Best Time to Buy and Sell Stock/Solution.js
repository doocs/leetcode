/**
 * @param {number[]} prices
 * @return {number}
 */
 var maxProfit = function(prices) {
  let res = 0;
  let mi = prices[0];
  for (let i = 1; i < prices.length; ++i) {
      res = Math.max(res, prices[i] - mi);
      mi = Math.min(mi, prices[i]);
  }
  return res;
};