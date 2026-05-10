class Solution {
public:
    vector<int> scoreValidator(vector<string>& events) {
        int score = 0;
        int counter = 0;
        for (string event : events) {
            if (isdigit(event[0])) {
                score += stoi(event);
            } else if (event == "W") {
                if (++counter == 10) {
                    break;
                }
            } else {
                score++;
            }
        }
        return {score, counter};
    }
};
