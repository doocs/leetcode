class Solution {
public:
    vector<int> deckRevealedIncreasing(vector<int>& deck) {
        sort(deck.rbegin(), deck.rend());
        deque<int> q;
        for (int v : deck) {
            if (!q.empty()) {
                q.push_front(q.back());
                q.pop_back();
            }
            q.push_front(v);
        }
        return vector<int>(q.begin(), q.end());
    }
};