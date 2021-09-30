#include <iostream>
#include <vector>

using namespace std;

void printvec( const vector<int> &vec, const string &strbegin = "", const string &strend = "" )
{
	cout << strbegin << endl;
	for ( auto val : vec )
	{
		cout << val << "\t";
	}

	cout << endl;
	cout << strend << endl;
}


void mergesort( vector<int> & vec, int left, int right )
{
	if ( left >= right )
	{
		return;
	}

	int mid = left + (right - left) / 2;
	mergesort( vec, left, mid );
	mergesort( vec, mid + 1, right );

	int i = left;
	int j = mid + 1;
	int k = 0;
	vector<int>	vecTmp;
	while ( i <= mid && j <= right )
	{
		if ( vec[i] < vec[j] )
		{
			vecTmp.push_back( vec[i] );
			i++;
		}else  {
			vecTmp.push_back( vec[j] );
			j++;
		}
	}

	while ( i <= mid )
	{
		vecTmp.push_back( vec[i] );
		i++;
	}

	while ( j <= right )
	{
		vecTmp.push_back( vec[j] );
		j++;
	}

	for ( int i = left; i <= right; i++ )
	{
		vec[i] = vecTmp[i - left];
	}

	return;
}


int main( void )
{
	vector<int> vec = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
	printvec( vec );
	mergesort( vec, 0, vec.size() - 1 );
	printvec( vec, "after insert sort" );
	return(0);
}
