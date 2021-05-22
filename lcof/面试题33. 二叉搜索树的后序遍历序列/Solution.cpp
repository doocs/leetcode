class Solution {
public:
    bool verifyPostorder(vector<int>& postorder) {
        return verify(postorder, 0, postorder.size() - 1);
    }

    bool verify(vector<int>& postorder, int left, int right) {
        if (left >= right) {
            return true;
        }
        int root = postorder[right], i = left;
        while (postorder[i] < root) {
            ++i;
        }
        int mid = i;
        while (i < right) {
            if (postorder[i] < root) {
                return false;
            }
            ++i;
        }
        return verify(postorder, left, mid - 1) && verify(postorder, mid, right - 1);
    }
};
