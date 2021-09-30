#include <iostream>
#include <vector>
#include <string>

using namespace std;

/* 简单版本 */
void bubblesort(vector<int> &vec)
{
    for (int i = 0; i < vec.size() - 1; i++)
    {
        for (int j = 0; j < vec.size() - i - 1; j++)
        {
            if (vec[j] > vec[j + 1])
            {
                swap(vec[j], vec[j + 1]);
            }
        }
    }
}

/* 改进版本 */
void bubblesort1(vector<int> &vec)
{
    for (int i = 0; i < vec.size() - 1; i++)
    {
        bool exchange = false;
        for (int j = 0; j < vec.size() - i - 1; j++)
        {
            if (vec[j] > vec[j + 1])
            {
                swap(vec[j], vec[j + 1]);
                exchange = true;
            }
        }

        if (!exchange)
        {
            break;
        }
    }
}

void printvec(const vector<int> &vec, const string &strbegin = "", const string &strend = "")
{
    cout << strbegin << endl;
    for (auto val : vec)
    {
        cout << val << "\t";
    }

    cout << endl;
    cout << strend << endl;
}

int main(void)
{
    vector<int> vec = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    printvec(vec);

    bubblesort1(vec);

    printvec(vec, "after sort", "");
}
