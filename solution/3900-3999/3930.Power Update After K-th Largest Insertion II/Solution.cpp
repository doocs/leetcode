#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
#include <vector>

using namespace std;
using namespace __gnu_pbds;

template <typename T>
using ordered_multiset = tree<pair<T, int>, null_type, less<pair<T, int>>,
    rb_tree_tag, tree_order_statistics_node_update>;

class Solution {
public:
    vector<int> powerUpdate(vector<int>& nums, int p, vector<vector<int>>& queries) {
        vector<int> ans;
        ordered_multiset<int> sl;
        const int mod = 1e9 + 7;

        for (int i = 0; i < nums.size(); i++) {
            sl.insert({nums[i], i});
        }

        int next_id = nums.size();

        auto mod_pow = [&](long long base, long long exp) -> long long {
            long long result = 1;
            base %= mod;
            while (exp > 0) {
                if (exp & 1) result = (result * base) % mod;
                base = (base * base) % mod;
                exp >>= 1;
            }
            return result;
        };

        for (const auto& query : queries) {
            int val = query[0];
            int k = query[1];

            sl.insert({val, next_id++});

            auto it = sl.find_by_order(sl.size() - k);
            int kth_largest = it->first;

            p = mod_pow(p, kth_largest);
            ans.push_back(p);
        }

        return ans;
    }
};
