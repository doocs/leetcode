class Solution {
public:
  string buildPalindrome(string left, char middle, int n) {

    string right = left;
    reverse(right.begin(), right.end());

    if (n % 2 == 1) {
      return left + string(1, middle) + right;
    }

    return left + right;
  }

  string lexPalindromicPermutation(string s, string target) {

    int n = s.size();
    vector<int> freq(26, 0);

    for (char c : s) {
      freq[c - 'a']++;
    }

    int oddCount = 0;
    char middle = 0;

    for (int i = 0; i < 26; i++) {

      if (freq[i] % 2 == 1) {
        oddCount++;
        middle = char('a' + i);
      }
    }

    if (oddCount > 1) {
      return "";
    }

    vector<int> halfFreq(26, 0);

    for (int i = 0; i < 26; i++) {
      halfFreq[i] = freq[i] / 2;
    }

    int halfLen = n / 2;

    string targetHalf = target.substr(0, halfLen);
    vector<int> remaining = halfFreq;
    string prefix = "";

    int matched = 0;

    for (int i = 0; i < halfLen; i++) {

      int x = targetHalf[i] - 'a';

      if (remaining[x] == 0) {
        break;
      }

      prefix += targetHalf[i];
      remaining[x]--;

      matched++;
    }

    if (matched == halfLen) {

      string candidate = buildPalindrome(prefix, middle, n);

      if (candidate > target) {
        return candidate;
      }

    }

    int lastPosition;

    if (matched == halfLen) {
      lastPosition = halfLen - 1;
    } else {
      lastPosition = matched;
    }

    for (int pos = lastPosition; pos >= 0; pos--) {

      vector<int> rem = halfFreq;

      bool validPrefix = true;

      for (int i = 0; i < pos; i++) {

        int x = targetHalf[i] - 'a';

        if (rem[x] == 0) {
          validPrefix = false;
          break;
        }

        rem[x]--;
      }

      if (!validPrefix) {
        continue;
      }

      int targetChar = targetHalf[pos] - 'a';

      for (int c = targetChar + 1; c < 26; c++) {

        if (rem[c] == 0) {
          continue;
        }
        string left = targetHalf.substr(0, pos);

        left += char('a' + c);

        rem[c]--;

        for (int x = 0; x < 26; x++) {

          while (rem[x] > 0) {

            left += char('a' + x);

            rem[x]--;
          }
        }

        string candidate = buildPalindrome(left, middle, n);

        if (candidate > target) {
          return candidate;
        }
        rem = halfFreq;

        for (int i = 0; i < pos; i++) {
          rem[targetHalf[i] - 'a']--;
        }
      }
    }

    return "";
  }
};