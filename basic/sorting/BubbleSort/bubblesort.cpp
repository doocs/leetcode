#include<iostream>
#include<vector>
#include<string>

using namespace std;

void bubblesort(vector<int>& vec)
{
    for(int i=0;i<vec.size()-1;i++)
    {
        for (int j=0;j<vec.size()-i-1;j++)
        {
            if(vec[j]>vec[j+1])
            {
                int tmp = vec[j];
                vec[j] = vec[j+1];
                vec[j+1] = tmp;
            }
        }
    }
}

void printvec(const vector<int>& vec, const string& strbegin="", const string& strend="")
{
    cout <<strbegin << endl;
    for( auto val:vec )
    {
        cout <<val << "\t";
    } 

    cout << endl;
    cout << strend<< endl;
}

int main(void)
{
    vector<int> vec = {9, 8, 7, 6, 5, 4, 3, 2 , 1, 0};
    printvec(vec);

    bubblesort(vec);

    printvec(vec, "after sort", "");
}
