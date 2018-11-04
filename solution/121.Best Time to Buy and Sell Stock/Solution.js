const maxProfit1 = function(prices){
  let min = prices[0];
  let profit = 0;
  for(let i = 0; i < prices.length; i++){
    if(prices[i] < min){
      min = prices[i];
    }
    if(profit < (prices[i] - min)){
      profit = prices[i] - min;
    }
  }
  return profit;
}