class Solution {
public:
  int maxFreeTime(int eventTime, vector<int>& startTime, vector<int>& endTime) {
    int n = startTime.size();
    int max_gap_before = 0;
    int last_end = 0;
    int max_free_time = 0;

    for (int i = 0; i < n; ++i) {
      int meeting_time = endTime[i] - startTime[i];
      int next_start = (i == n - 1) ? eventTime : startTime[i + 1];
      int free_time = next_start - last_end;
      if (meeting_time > max_gap_before) free_time -= meeting_time;
      max_free_time = max(max_free_time, free_time);
      max_gap_before = max(max_gap_before, startTime[i] - last_end);
      last_end = endTime[i];
    }

    int max_gap_after = 0;
    int last_start = eventTime;
    for (int i = n - 1; i >= 0; --i) {
      int meeting_time = endTime[i] - startTime[i];
      int prev_end = (i == 0) ? 0 : endTime[i - 1];
      int free_time = last_start - prev_end;
      if (meeting_time <= max_gap_after)
        max_free_time = max(max_free_time, free_time);
      max_gap_after = max(max_gap_after, last_start - endTime[i]);
      last_start = startTime[i];
    }

    return max_free_time;
  }
};