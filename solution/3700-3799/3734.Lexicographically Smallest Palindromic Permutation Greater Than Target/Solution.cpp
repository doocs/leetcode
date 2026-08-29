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

    // -----------------------------------------
    // STEP 1: Count frequencies
    // -----------------------------------------

    vector<int> freq(26, 0);

    for (char c : s) {
      freq[c - 'a']++;
    }

    // -----------------------------------------
    // STEP 2: Check if palindrome is possible
    // -----------------------------------------

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

    // -----------------------------------------
    // STEP 3: Frequency for LEFT HALF
    // -----------------------------------------

    vector<int> halfFreq(26, 0);

    for (int i = 0; i < 26; i++) {
      halfFreq[i] = freq[i] / 2;
    }

    int halfLen = n / 2;

    string targetHalf = target.substr(0, halfLen);

    // -----------------------------------------
    // STEP 4:
    // Try to construct targetHalf exactly
    // -----------------------------------------

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

    // -----------------------------------------
    // STEP 5:
    // If we constructed the entire targetHalf,
    // check its palindrome.
    // -----------------------------------------

    if (matched == halfLen) {

      string candidate = buildPalindrome(prefix, middle, n);

      if (candidate > target) {
        return candidate;
      }

      /*
          candidate == target

          So now we need the NEXT greater
          permutation of targetHalf.
      */
    }

    // -----------------------------------------
    // STEP 6:
    // Backtracking
    //
    // We try to increase the RIGHTMOST possible
    // position.
    // -----------------------------------------

    /*
        Suppose:

            targetHalf = "abc"

        and we matched:

            prefix = "ab"

        We first try:

            ab -> ac

        If impossible:

            ab -> ?

        Then backtrack:

            a -> b

        etc.
    */

    int lastPosition;

    if (matched == halfLen) {
      lastPosition = halfLen - 1;
    } else {
      lastPosition = matched;
    }

    for (int pos = lastPosition; pos >= 0; pos--) {

      // -----------------------------------------
      // Rebuild frequency available BEFORE pos
      // -----------------------------------------

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

      // -----------------------------------------
      // At this position, we need a character
      // GREATER than targetHalf[pos]
      // -----------------------------------------

      int targetChar = targetHalf[pos] - 'a';

      for (int c = targetChar + 1; c < 26; c++) {

        if (rem[c] == 0) {
          continue;
        }

        // -----------------------------------------
        // Build new left half
        // -----------------------------------------

        string left = targetHalf.substr(0, pos);

        // Put smallest possible greater character
        left += char('a' + c);

        rem[c]--;

        // -----------------------------------------
        // Fill remaining positions with the
        // smallest available characters
        // -----------------------------------------

        for (int x = 0; x < 26; x++) {

          while (rem[x] > 0) {

            left += char('a' + x);

            rem[x]--;
          }
        }

        // -----------------------------------------
        // Build palindrome
        // -----------------------------------------

        string candidate = buildPalindrome(left, middle, n);

        if (candidate > target) {
          return candidate;
        }

        // Restore is not necessary because rem
        // is recreated for every `c` iteration
        rem = halfFreq;

        for (int i = 0; i < pos; i++) {
          rem[targetHalf[i] - 'a']--;
        }
      }
    }

    return "";
  }
};