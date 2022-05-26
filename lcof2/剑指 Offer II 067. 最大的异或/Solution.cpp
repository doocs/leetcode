class Trie {
public:
    Trie* left;
    Trie* right;
};

class Solution {
public:
    int highest = 30;
    Trie* root;

    int findMaximumXOR(vector<int>& nums) {
        root = new Trie();
        int res = 0;
        for (int i = 1; i < nums.size(); ++i)
        {
            add(nums[i - 1]);
            res = max(res, cal(nums[i]));
        }
        return res;
    }

    int cal(int num) {
        Trie* node = root;
        int res = 0;
        for (int i = highest; i >= 0; --i)
        {
            int bit = (num >> i) & 1;
            if (bit == 0)
            {
                if (node->right)
                {
                    res = res * 2 + 1;
                    node = node->right;
                }
                else
                {
                    res = res * 2;
                    node = node->left;
                }
            }
            else
            {
                if (node->left)
                {
                    res = res * 2 + 1;
                    node = node->left;
                }
                else
                {
                    res = res * 2;
                    node = node->right;
                }
            }
        }
        return res;
    }

    void add(int num) {
        Trie* node = root;
        for (int i = highest; i >= 0; --i)
        {
            int bit = (num >> i) & 1;
            if (bit == 0)
            {
                if (!node->left) node->left = new Trie();
                node = node->left;
            }
            else
            {
                if (!node->right) node->right = new Trie();
                node = node->right;
            }
        }
    }
};