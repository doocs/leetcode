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

void insertsort(vector<int>& vec) {
    for (int i = 1; i < vec.size(); i++) {
        int j = i - 1;
        int num = vec[i];
        for (; j >= 0 && vec[j] > num; j--) {
            vec[j + 1] = vec[j];
        }

        vec[j + 1] = num;
    }

    return;
}

int main() {
    vector<int> vec = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    printvec(vec);
    insertsort(vec);
    printvec(vec, "after insert sort");
    return (0);
}
