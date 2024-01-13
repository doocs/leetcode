struct Task {
    int n;
    vector<int>* a;
    vector<int>* b;
    vector<int>* c;
};

class Solution {
public:
    void hanota(vector<int>& A, vector<int>& B, vector<int>& C) {
        stack<Task> stk;
        stk.push({(int) A.size(), &A, &B, &C});
        while (!stk.empty()) {
            Task task = stk.top();
            stk.pop();
            if (task.n == 1) {
                task.c->push_back(task.a->back());
                task.a->pop_back();
            } else {
                stk.push({task.n - 1, task.b, task.a, task.c});
                stk.push({1, task.a, task.b, task.c});
                stk.push({task.n - 1, task.a, task.c, task.b});
            }
        }
    }
};