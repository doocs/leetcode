class ATM {
public:
    ATM() {
    }

    void deposit(vector<int> banknotesCount) {
        for (int i = 0; i < banknotesCount.size(); ++i) {
            cnt[i] += banknotesCount[i];
        }
    }

    vector<int> withdraw(int amount) {
        vector<int> ans(m);
        for (int i = m - 1; ~i; --i) {
            ans[i] = min(1ll * amount / d[i], cnt[i]);
            amount -= ans[i] * d[i];
        }
        if (amount > 0) {
            return {-1};
        }
        for (int i = 0; i < m; ++i) {
            cnt[i] -= ans[i];
        }
        return ans;
    }

private:
    static constexpr int d[5] = {20, 50, 100, 200, 500};
    static constexpr int m = size(d);
    long long cnt[m] = {0};
};

/**
 * Your ATM object will be instantiated and called as such:
 * ATM* obj = new ATM();
 * obj->deposit(banknotesCount);
 * vector<int> param_2 = obj->withdraw(amount);
 */
