#include <vector>
#include <iostream>

using namespace std;

class Solution
{
public:
	int singleNumber( vector<int> & nums )
	{
		int ans = 0;
		for ( int i = 0; i < 32; i++ )
		{
			int cnt = 0;
			for ( int j = 0; j < nums.size(); j++ )
			{
				cnt += ( (nums[j] >> i) & 1);
			}

			cnt	%= 3;
			ans	|= (cnt << i);
		}

		return(ans);
	}
};

void printvec( vector<int> & vec )
{
	for ( int i = 0; i < vec.size(); i++ )
	{
		cout << vec[i] << " ";
	}

	cout << endl;
}


int main( void )
{
	Solution	test;
	vector<int>	nums	= { 2, 2, 2, 1, 1, 1, 3, 3, 3, 6 };
	int		res	= test.singleNumber( nums );
	printvec( nums );
	cout << res << endl;

	return(0);
}


