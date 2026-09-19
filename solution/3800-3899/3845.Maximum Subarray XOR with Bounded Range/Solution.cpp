class Solution {
public:
    int maxXor(vector<int>& nums, int k) {
        root = new TrieNode();
        int n = nums.size();
        vector<int> prefix(n + 1);
        for (int i = 0; i < n; ++i) {
            prefix[i + 1] = prefix[i] ^ nums[i];
        }
        deque<int> maxq, minq;
        int left = 0, ans = 0;
        update(prefix[0], 1);
        for (int right = 0; right < n; ++right) {
            while (!maxq.empty() && nums[maxq.back()] <= nums[right]) {
                maxq.pop_back();
            }
            while (!minq.empty() && nums[minq.back()] >= nums[right]) {
                minq.pop_back();
            }
            maxq.push_back(right);
            minq.push_back(right);
            while (nums[maxq.front()] - nums[minq.front()] > k) {
                if (maxq.front() == left) {
                    maxq.pop_front();
                }
                if (minq.front() == left) {
                    minq.pop_front();
                }
                update(prefix[left], -1);
                ++left;
            }
            ans = max(ans, getMaxXor(prefix[right + 1]));
            update(prefix[right + 1], 1);
        }
        return ans;
    }

private:
    struct TrieNode {
        TrieNode* children[2]{};
        int count = 0;
    };

    TrieNode* root;

    void update(int value, int delta) {
        TrieNode* cur = root;
        for (int bit = 14; bit >= 0; --bit) {
            int b = (value >> bit) & 1;
            if (!cur->children[b]) {
                cur->children[b] = new TrieNode();
            }
            cur = cur->children[b];
            cur->count += delta;
        }
    }

    int getMaxXor(int value) {
        TrieNode* cur = root;
        int ans = 0;
        for (int bit = 14; bit >= 0; --bit) {
            int b = (value >> bit) & 1;
            int opp = 1 - b;
            if (cur->children[opp] && cur->children[opp]->count > 0) {
                ans |= 1 << bit;
                cur = cur->children[opp];
            } else {
                cur = cur->children[b];
            }
        }
        return ans;
    }
};
