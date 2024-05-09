#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/hash_policy.hpp>
using namespace __gnu_pbds;

template <class T>
using ordered_set = tree<T, null_type, less<T>, rb_tree_tag, tree_order_statistics_node_update>;

class SORTracker {
public:
    SORTracker() {
    }

    void add(string name, int score) {
        st.insert({-score, name});
    }

    string get() {
        return st.find_by_order(++i)->second;
    }

private:
    ordered_set<pair<int, string>> st;
    int i = -1;
};

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker* obj = new SORTracker();
 * obj->add(name,score);
 * string param_2 = obj->get();
 */