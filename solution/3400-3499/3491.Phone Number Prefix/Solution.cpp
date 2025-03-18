#include <ranges>

class Solution {
public:
    bool phonePrefix(vector<string>& numbers) {
        ranges::sort(numbers, [](const string& a, const string& b) {
            return a.size() < b.size();
        });
        for (int i = 0; i < numbers.size(); i++) {
            if (ranges::any_of(numbers | views::take(i), [&](const string& t) {
                    return numbers[i].starts_with(t);
                })) {
                return false;
            }
        }
        return true;
    }
};
