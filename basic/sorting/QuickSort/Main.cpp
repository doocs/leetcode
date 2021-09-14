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


int partition( vector<int> & vec, int left, int right )
{
	if ( left >= right )
	{
		return left;
	}

	int base = vec[left];
	while ( left < right )
	{
		while ( left < right && vec[right] >= base )
		{
			right--;
		}
		if ( left >= right )
		{
			break;
		}

		vec[left] = vec[right];

		while ( left < right && vec[left] <= base )
		{
			left++;
		}

		if ( left >= right )
		{
			break;
		}

		vec[right] = vec[left];
	}

	vec[left] = base;
	return left;
}


void quicksort( vector<int> & vec, int left, int right )
{
	if ( left >= right )
	{
		return;
	}

	int idx = partition( vec, left, right );
	quicksort( vec, left, idx - 1 );
	quicksort( vec, idx + 1, right );
}


int main( void )
{
	vector<int> vec = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
	printvec( vec );
	quicksort( vec, 0, vec.size() - 1 );
	printvec( vec, "after insert sort" );
	return 0;
}



