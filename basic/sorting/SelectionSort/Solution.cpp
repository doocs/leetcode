#include <cstddef>
#include <iostream>
#include <vector>

using namespace std;

void printvec(const vector<int>& vec, const string& strbegin = "", const string& strend = "") {
    cout << strbegin << endl;
    for (auto val : vec) {
        cout << val << "\t";
    }

    cout << endl;
    cout << strend << endl;
}

void selectsort(vector<int>& vec) {
    for (std::size_t i = 0; i + 1 < vec.size(); ++i) {
        std::size_t minidx = i;
        for (std::size_t j = i + 1; j < vec.size(); ++j) {
            if (vec[minidx] > vec[j]) {
                minidx = j;
            }
        }

        swap(vec[i], vec[minidx]);
    }
}

int main(void) {
    vector<int> vec = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    printvec(vec);
    selectsort(vec);
    printvec(vec, "after insert sort");
    return (0);
}
