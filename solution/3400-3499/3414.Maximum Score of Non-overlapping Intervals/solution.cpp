class Solution {
private:
    static constexpr int MAX_COUNT = 4;

    struct Result {
        long long weight = 0;
        array<int, MAX_COUNT> indices{};
        int size = 0;
    };

    // a.indices가 b.indices보다 사전순으로 작은지 비교
    static bool lexicographicallyLess(const Result& a, const Result& b) {
        const int commonSize = min(a.size, b.size);

        for (int i = 0; i < commonSize; ++i) {
            if (a.indices[i] != b.indices[i]) {
                return a.indices[i] < b.indices[i];
            }
        }

        return a.size < b.size;
    }

    // weight가 크면 우선.
    // weight가 같으면 인덱스 배열이 사전순으로 작은 쪽을 우선.
    static bool isBetter(const Result& a, const Result& b) {
        if (a.weight != b.weight) {
            return a.weight > b.weight;
        }

        return lexicographicallyLess(a, b);
    }

    // 정렬된 인덱스 배열에 index를 삽입
    static Result addInterval(
        const Result& suffix,
        int index,
        long long weight
    ) {
        Result result;
        result.weight = suffix.weight + weight;
        result.size = suffix.size + 1;

        int suffixPos = 0;
        int resultPos = 0;

        while (
            suffixPos < suffix.size &&
            suffix.indices[suffixPos] < index
        ) {
            result.indices[resultPos++] = suffix.indices[suffixPos++];
        }

        result.indices[resultPos++] = index;

        while (suffixPos < suffix.size) {
            result.indices[resultPos++] = suffix.indices[suffixPos++];
        }

        return result;
    }

public:
    vector<int> maximumWeight(vector<vector<int>>& intervals) {
        const int n = static_cast<int>(intervals.size());

        // [left, right, weight, originalIndex]
        for (int i = 0; i < n; ++i) {
            intervals[i].push_back(i);
        }

        ranges::sort(intervals);

        // nextIndex[i]:
        // intervals[i]를 선택했을 때 다음으로 선택 가능한 첫 구간
        vector<int> nextIndex(n);

        for (int i = 0; i < n; ++i) {
            const int right = intervals[i][1];

            auto it = lower_bound(
                intervals.begin(),
                intervals.end(),
                right + 1,
                [](const vector<int>& interval, int targetLeft) {
                    return interval[0] < targetLeft;
                }
            );

            nextIndex[i] = static_cast<int>(it - intervals.begin());
        }

        // dp[i][count]:
        // i번 이후의 구간에서 최대 count개를 골랐을 때의 최적 결과
        vector<array<Result, MAX_COUNT + 1>> dp(n + 1);

        for (int i = n - 1; i >= 0; --i) {
            for (int count = 1; count <= MAX_COUNT; ++count) {
                // 현재 구간을 선택하지 않는 경우
                const Result& skip = dp[i + 1][count];

                // 현재 구간을 선택하는 경우
                Result take = addInterval(
                    dp[nextIndex[i]][count - 1],
                    intervals[i][3],
                    intervals[i][2]
                );

                dp[i][count] = isBetter(take, skip)
                    ? take
                    : skip;
            }
        }

        const Result& answer = dp[0][MAX_COUNT];

        return vector<int>(
            answer.indices.begin(),
            answer.indices.begin() + answer.size
        );
    }
};
