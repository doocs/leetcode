public:
    int maxProfit(vector<int>& prices) 
	{
        if (0 == prices.size())
            return 0 ;
        int M = 0 ;
        for (int i = 1; i < prices.size(); ++i)
        {
            int d = prices[i] - prices[i-1] ;
            if (d > 0)
                M += d ;
        }
        return M ;
    }
};